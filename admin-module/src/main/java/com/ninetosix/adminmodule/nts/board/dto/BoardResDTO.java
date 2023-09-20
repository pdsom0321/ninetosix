package com.ninetosix.adminmodule.nts.board.dto;

import com.ninetosix.coremodule.entity.Board;
import com.ninetosix.coremodule.vo.YNCode;

public record BoardResDTO(String type, long id, String title, String content, String startDate, String endDate, YNCode useYn, YNCode deleteYn) {
    public static BoardResDTO of(Board board) {
        return new BoardResDTO(board.getType().name(), board.getId(), board.getTitle(), board.getContent(), board.getStartDate(), board.getEndDate(), board.getUseYn(), board.getDeleteYn());
    }
}
