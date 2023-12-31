package com.ninetosix.apimodule.nts.controller;

import com.ninetosix.apimodule.nts.dto.board.BoardResDTO;
import com.ninetosix.apimodule.nts.dto.board.BoardsResDTO;
import com.ninetosix.apimodule.nts.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글 목록")
    @GetMapping("/board/{type}")
    public ResponseEntity<List<BoardsResDTO>> boards(@PathVariable String type) {
        return ResponseEntity.ok(boardService.boards(type));
    }

    @ApiOperation(value = "게시글 상세")
    @GetMapping("/board/{type}/{id}")
    public ResponseEntity<BoardResDTO> board(@PathVariable String type, @PathVariable Long id) {
        return ResponseEntity.ok(boardService.board(type, id));
    }
}
