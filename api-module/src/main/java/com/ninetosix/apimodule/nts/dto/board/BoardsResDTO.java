package com.ninetosix.apimodule.nts.dto.board;

import com.ninetosix.coremodule.entity.Board;

import java.time.LocalDateTime;

public record BoardsResDTO(Long id, String title, LocalDateTime startDate, LocalDateTime endDate) {
    public static BoardsResDTO of(Board board) {
        return new BoardsResDTO(board.getId(), board.getTitle(), board.getStartDate(), board.getEndDate());
    }
}

