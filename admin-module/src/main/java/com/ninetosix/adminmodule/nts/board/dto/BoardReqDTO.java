package com.ninetosix.adminmodule.nts.board.dto;

import com.ninetosix.coremodule.vo.YNCode;

public record BoardReqDTO(
        String title,
        String content,
        String startDate,
        String endDate,
        YNCode useYn,
        YNCode deleteYn) {
}
