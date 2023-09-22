package com.ninetosix.adminmodule.nts.board.dto;

import com.ninetosix.coremodule.entity.Board;
import com.ninetosix.coremodule.vo.YNCode;
public record BoardsResDTO(
        long id,
        String title,
        String startDate,
        String endDate,
        YNCode useYn,
        YNCode deleteYn) {
    public static BoardsResDTO of(Board board) {
        return new BoardsResDTO(board.getId(), board.getTitle(), board.getStartDate(), board.getEndDate(), board.getUseYn(), board.getDeleteYn());
    }
}
