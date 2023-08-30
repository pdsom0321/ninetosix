package com.ninetosix.apimodule.nts.dto.attend;

import com.ninetosix.coremodule.entity.Attend;

public record  AttendResDTO(String attendCode, String attendDate, String inTime, String outTime) {
    public static AttendResDTO of(Attend attend) {
        return new AttendResDTO(attend.getAttendCode(), attend.getAttendDate(), attend.getInTime(), attend.getOutTime());
    }
}