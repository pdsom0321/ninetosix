package com.ninetosix.adminmodule.nts.board.dto;

public record BoardModifyReqDTO(
        String title,
        String content,
        String startDate,
        String endDate,
        String useYn,
        String deleteYn) {
}
