package com.ninetosix.adminmodule.nts.board.controller;

import com.ninetosix.adminmodule.nts.board.dto.BoardModifyReqDTO;
import com.ninetosix.adminmodule.nts.board.dto.BoardReqDTO;
import com.ninetosix.adminmodule.nts.board.dto.BoardResDTO;
import com.ninetosix.adminmodule.nts.board.dto.BoardsResDTO;
import com.ninetosix.adminmodule.nts.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글 등록")
    @PostMapping("board/{type}")
    public ResponseEntity<Void> createBoard(@PathVariable String type, @RequestBody BoardReqDTO reqDTO) {
        boardService.createBoard(type, reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "type 별 게시글 목록")
    @GetMapping("board/{type}")
    public ResponseEntity<List<BoardsResDTO>> boards(@PathVariable String type) {
        return ResponseEntity.ok(boardService.boards(type));
    }

    @ApiOperation(value = "게시글 상세 목록")
    @GetMapping("board/{type}/{id}")
    public ResponseEntity<BoardResDTO> board(@PathVariable String type, @PathVariable long id) {
        return ResponseEntity.ok(boardService.board(type, id));
    }

    @ApiOperation(value = "게시글 수정")
    @PutMapping("board/{id}")
    public ResponseEntity<Void> modifyBoard(@PathVariable long id, @RequestBody BoardModifyReqDTO reqDTO) {
        boardService.modifyBoard(id, reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("board/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
