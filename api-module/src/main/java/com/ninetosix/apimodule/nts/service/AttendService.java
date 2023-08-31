package com.ninetosix.apimodule.nts.service;

import com.ninetosix.apimodule.core.jwt.MemberContext;
import com.ninetosix.apimodule.nts.dto.attend.*;
import com.ninetosix.coremodule.entity.Attend;
import com.ninetosix.coremodule.entity.Member;
import com.ninetosix.coremodule.repository.AttendRepository;
import com.ninetosix.coremodule.vo.AttendCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}