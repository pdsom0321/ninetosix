package com.ninetosix.apimodule.nts.dto.attend;


import com.ninetosix.coremodule.entity.Attend;

public record AttendDTO(String attendDate, String inTime, String outTime, Long workTime, Long memberId) {
    public static AttendDTO of(Attend attend) {
        return new AttendDTO(attend.getAttendDate().substring(6), attend.getInTime(), attend.getOutTime(), attend.getWorkTime(), attend.getMember().getId());
    }
}
