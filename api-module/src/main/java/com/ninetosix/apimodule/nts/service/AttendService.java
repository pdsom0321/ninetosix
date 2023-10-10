package com.ninetosix.apimodule.nts.service;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Value;
import com.ninetosix.apimodule.core.jwt.MemberContext;
import com.ninetosix.apimodule.nts.dto.attend.*;
import com.ninetosix.coremodule.entity.Attend;
import com.ninetosix.coremodule.entity.Member;
import com.ninetosix.coremodule.repository.AttendRepository;
import com.ninetosix.coremodule.vo.AttendCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AttendService {
    private final AttendRepository attendRepository;
    private final MemberService memberService;

    @Value("${server.doc-path}")
    private String docPath;

//    private int startRow = 6;
//    private int startCol = 2;

    public void onWork(OnWorkReqDTO reqDTO) {
        Long memberId = MemberContext.getMemberId();
        Member member = memberService.findById(memberId);

        attendRepository.save(Attend.createAttend(getCurrentDate(), getCurrentTime(), reqDTO.attendCode(), reqDTO.locationId(), member));
    }

    public void onWorkDuringDayOff(OnWorkDuringDayOffReqDTO reqDTO) {
        Long memberId = MemberContext.getMemberId();

        Attend attend = attendRepository.findByAttendDateAndMemberId(getCurrentDate(), memberId)
                .orElseThrow(() -> new NoSuchElementException("attend 정보가 없습니다."));

        attend.updateInTimeAndLocationId(getCurrentTime(), reqDTO.locationId());
    }

    public void offWork() {
        Long memberId = MemberContext.getMemberId();

        Attend attend = attendRepository.findByAttendDateAndMemberId(getCurrentDate(), memberId)
                .orElseThrow(() -> new NoSuchElementException("attend 정보가 없습니다."));

        attend.updateOutTimeAndWorkTime(getCurrentTime());
    }

    public void dayOff(String attendCode, AttendCodeReqDTO reqDTO) {
        int from = reqDTO.from();
        int to = reqDTO.to();
        Long memberId = MemberContext.getMemberId();
        Member member = memberService.findById(memberId);

        IntStream.rangeClosed(from, to)
                .forEach(current -> {
                    Attend attend = attendRepository.findByAttendDateAndMemberId(String.valueOf(current), memberId)
                            .orElse(Attend.createAttendDayOff(String.valueOf(current), member));

                    attend.updateCode(attendCode);
                    attendRepository.save(attend);
                });
    }

    public void cancelDayOff(String day) {
        Long memberId = MemberContext.getMemberId();

        Attend attend = attendRepository.findByAttendDateAndMemberId(day, memberId)
                .orElseThrow(() -> new NoSuchElementException("attend 정보가 없어 신청한 휴가정보를 철회할 수 없습니다."));

        Optional.ofNullable(attend.getInTime())
                .ifPresentOrElse(o -> attend.updateCode(AttendCode.DAY.getAttendCode()),
                        () -> attendRepository.deleteById(attend.getId()));
    }

    public AttendResDTO attendInfo(long memberId) {
        Attend attend = attendRepository.findByAttendDateAndMemberId(getCurrentDate(), memberId)
                .orElseGet(Attend::new);

        return AttendResDTO.of(attend);
    }

    public List<AttendResDTO> yesterdayAndTodayAttendanceList(Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        String startDate = now.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String endDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<String> dayList = List.of(startDate, endDate);
        return dayList.stream()
                .sorted()
                .map(day -> attendRepository.findByAttendDateAndMemberId(day, memberId)
                        .orElseGet(Attend::new))
                .map(AttendResDTO::of)
                .collect(Collectors.toList());
    }

    public List<MonthlyResDTO> monthlyAttendanceList(Long memberId, String month) {
        List<Attend> attendList = attendRepository.findByMemberIdAndAttendDateContainsOrderByAttendDateAsc(memberId, month);

        return attendList.stream()
                // .filter(attend -> Objects.nonNull(attend.getInTime()))
                .map(MonthlyResDTO::of)
                .collect(Collectors.toList());
    }

    public List<Integer> getDayOfMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int lastDayOfMonth = yearMonth.lengthOfMonth();

        return IntStream.rangeClosed(1, lastDayOfMonth)
                .mapToObj(yearMonth::atDay)
                .map(LocalDate::getDayOfMonth)
                .collect(Collectors.toList());
    }

    public List<ExportDTO> getAttends(int year, int month, Long teamId) {
        return memberService.findAllByTeamId(teamId).stream()
                .map(member -> {
                    List<AttendDTO> list = monthlyMembersAttendanceListForExport(member.getId(), String.format("%04d%02d", year, month));
                    return new ExportDTO(member.getName(), list);
                })
                .sorted(Comparator.comparing(ExportDTO::memberName))
                .toList();
    }

    public List<AttendDTO> monthlyMembersAttendanceListForExport(Long memberId, String month) {
        return attendRepository.findByMemberIdAndAttendDateStartsWith(memberId, month).stream()
                .map(AttendDTO::of)
                .collect(Collectors.toList());
    }

    private String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
    }

    public void exportPOI(int year, int month, Long teamId, HttpServletResponse response) {
        try {
            //엑셀 템플릿 로드
            FileInputStream file = new FileInputStream(new File(docPath + "/form1.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet workSheet = wb.getSheetAt(0);

            //해당 팀원 정보, 해당 월 날짜 정보
            List<ExportDTO> memberList = getAttends(year,month,teamId);
            List<Integer> dates = getDayOfMonth(year, month);

            workSheet.getRow(1).getCell(0).setCellValue(String.format("%02d",month) + "월 출근부");

            int row = 6;
            int col = 2;

            for(ExportDTO member : memberList) {

                //팀원이 6명이 넘어갈 경우 가로로 쭉 길어짐
                //6명 이후에는 새로 시트를 따서 새 폼으로 작성할 것
                //템플릿 불러오거나 새 시트 따는 부분을 별도 메소드로 분리 필요해보임

                //팀원 이름 셋팅
                workSheet.getRow(row).getCell(col).setCellValue(member.memberName());

                row = 8;

                for(int day : dates){

                    /*
                        if 주말? (공휴일도 제외할 수 있음 good)
                            row ~ row+2 색 회색
                    */

                    for(AttendDTO attend : member.attends()){
                        if(Integer.valueOf(attend.attendDate()).equals(day)){
                            //출근시간
                            workSheet.getRow(row).getCell(col).setCellValue(attend.inTime().substring(0,2));
                            workSheet.getRow(row).getCell(col+1).setCellValue(attend.inTime().substring(2,4));

                            //퇴근시간
                            workSheet.getRow(row+1).getCell(col).setCellValue(attend.outTime().substring(0,2));
                            workSheet.getRow(row+1).getCell(col+1).setCellValue(attend.outTime().substring(2,4));

                            //근무시간 계
                            workSheet.getRow(row+2).getCell(col).setCellValue(String.format("%02d",Math.floorDiv(attend.workTime(),60)));
                            workSheet.getRow(row+2).getCell(col+1).setCellValue(String.format("%02d",Math.floorMod(attend.workTime(),60)));
                        }
                    }
                    row += 3;
                }

                row = 6;
                col += 3;
            }

            //현재 row부터 103행까지 deleteRow

            String fileName = "[NineToSix] " + year + "년 " + month + "월 출근부";

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            response.setHeader("Content-Disposition", "attachment;filename=\""+
                    new String(fileName.getBytes("euc-kr"),"8859_1")+".xlsx\"");

            wb.write(response.getOutputStream());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}