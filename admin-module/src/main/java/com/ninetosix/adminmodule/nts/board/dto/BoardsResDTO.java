package com.ninetosix.adminmodule.nts.board.dto;

import java.util.List;

public record BoardsResDTO(String keyword, int page, int totalPage, List<BoardsDTO> list) {
}
