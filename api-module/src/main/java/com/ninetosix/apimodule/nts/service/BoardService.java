package com.ninetosix.apimodule.nts.service;

import com.ninetosix.apimodule.nts.dto.board.BoardResDTO;
import com.ninetosix.apimodule.nts.dto.board.BoardsResDTO;
import com.ninetosix.coremodule.entity.Board;
import com.ninetosix.coremodule.repository.BoardRepository;
import com.ninetosix.coremodule.vo.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResDTO board(String type, Long id) {
        Board board = boardRepository.findByIdAndType(id, BoardType.valueOf(type));
        return BoardResDTO.of(board);
    }

    public List<BoardsResDTO> boards(String type) {
        return boardRepository.findAllByType(BoardType.valueOf(type)).stream()
                .map(BoardsResDTO::of)
                .collect(Collectors.toList());
    }
}
