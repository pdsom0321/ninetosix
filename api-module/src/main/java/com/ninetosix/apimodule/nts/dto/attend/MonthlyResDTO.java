package com.ninetosix.apimodule.nts.dto.attend;

import com.ninetosix.coremodule.entity.Attend;

public record MonthlyResDTO(String attendCode, String attendDate, String inTime, String outTime, Long workTime) {
    public static MonthlyResDTO of(Attend attend) {
        return new MonthlyResDTO(attend.getAttendCode(), attend.getAttendDate(), attend.getInTime(), attend.getOutTime(), attend.getWorkTime());
    }
}
