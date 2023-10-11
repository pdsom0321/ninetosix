package com.ninetosix.apimodule.nts.service;

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

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
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

    public void onWork(OnWorkReqDTO reqDTO) {
        Long memberId = MemberContext.getMemberId();
        Member member = memberService.findById(memberId);

        attendRepository.save(Attend.createAttend(getCurrentDate(), getCurrentTime(), reqDTO.attendCode(), reqDTO.locationId(), member));
    }

    public void onWorkDuringDayOff(OnWorkDuringDayOffReqDTO reqDTO) {
        Long memberId = MemberContext.getMemberId();

        Attend attend = attendRepository.findByAttendDateAndMemberId(getCurrentDate(), memberId)
                .orElseThrow(() -> new EntityNotFoundException("attend 정보가 없습니다."));

        attend.updateInTimeAndLocationId(getCurrentTime(), reqDTO.locationId());
    }

    public void offWork() {
        Long memberId = MemberContext.getMemberId();

        Attend attend = attendRepository.findByAttendDateAndMemberId(getCurrentDate(), memberId)
                .orElseThrow(() -> new EntityNotFoundException("attend 정보가 없습니다."));

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
                .orElseThrow(() -> new EntityNotFoundException("attend 정보가 없어 신청한 휴가정보를 철회할 수 없습니다."));

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

    public List<ExportDTO> getAttends(long teamId, int year, int month) {
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

    // TODO : 팀원 6명 이상일 경우 다음 sheet로 넘어가도록 대응, 주말/공휴일인 경우 배경색 회색 처리
    public void downloadExcel(HttpServletResponse response, long teamId, int year, int month) {
        List<ExportDTO> memberList = getAttends(teamId, year, month);
        List<Integer> dates = getDayOfMonth(year, month);

        String templateFileName = "form1.xlsx";
        try (FileInputStream file = new FileInputStream(docPath + templateFileName);
             XSSFWorkbook wb = new XSSFWorkbook(file)) {

            XSSFSheet workSheet = wb.getSheetAt(0);
            workSheet.getRow(1).getCell(0).setCellValue(String.format("%02d", month) + "월 출근부");

            // 템플릿 내의 이름, 출근시간, 퇴근시간, 근무시간 데이터 셋팅
            int nameRow = 6;
            int row = 8;
            int col = 2;
            for (ExportDTO member : memberList) {
                //팀원 이름 셋팅
                workSheet.getRow(nameRow).getCell(col).setCellValue(member.memberName());

                for (int day : dates) {
                    for (AttendDTO attend : member.attends()) {
                        if (Integer.valueOf(attend.attendDate()).equals(day)) {
                            setCellValue(workSheet, row, col, attend.inTime());
                            setCellValue(workSheet, row + 1, col, attend.outTime());
                            setWorkTimeCellValue(workSheet, row + 2, col, attend.workTime());
                        }
                    }
                    // 다음 멤버(한명당 3개 행 차지)
                    row += 3;
                }
                row = 8;
                col += 3;
            }

            String fileName = "[NineToSix] " + year + "년 " + month + "월 출근부";

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            response.setHeader("Content-Disposition", "attachment;filename=\"" +
                    new String(fileName.getBytes("euc-kr"), "8859_1") + ".xlsx\"");

            wb.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValue(XSSFSheet workSheet, int row, int col, String time) {
        if (time != null) {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2, 4));
            workSheet.getRow(row).getCell(col).setCellValue(hour);
            workSheet.getRow(row).getCell(col + 1).setCellValue(minute);
        }
    }

    private void setWorkTimeCellValue(XSSFSheet workSheet, int row, int col, Long workTime) {
        if(workTime != null) {
            long hours = Math.floorDiv(workTime, 60);
            long minutes = Math.floorMod(workTime, 60);
            workSheet.getRow(row).getCell(col).setCellValue(String.format("%02d", hours));
            workSheet.getRow(row).getCell(col + 1).setCellValue(String.format("%02d", minutes));
        }
    }

    private String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
    }
}