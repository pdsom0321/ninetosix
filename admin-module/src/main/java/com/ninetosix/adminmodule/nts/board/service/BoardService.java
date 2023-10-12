package com.ninetosix.adminmodule.nts.board.service;

import com.ninetosix.adminmodule.nts.board.dto.*;
import com.ninetosix.coremodule.entity.Board;
import com.ninetosix.coremodule.repository.BoardRepository;
import com.ninetosix.coremodule.vo.BoardType;
import com.ninetosix.coremodule.vo.YNCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public void createBoard(String type, BoardReqDTO reqDTO) {
        boardRepository.save(Board.create(BoardType.valueOf(type), reqDTO.title(), reqDTO.content(), reqDTO.startDate(), reqDTO.endDate(), reqDTO.useYn(), reqDTO.deleteYn()));
    }

    @Transactional(readOnly = true)
    public BoardsResDTO boards(String type, Pageable pageable) {
        List<BoardsDTO> boards = boardRepository.findAllByType(BoardType.valueOf(type), pageable).stream()
                .map(BoardsDTO::of)
                .toList();

        long count = boardRepository.countAllByType(BoardType.valueOf(type));
        int pageSize = pageable.getPageSize();
        int totalPage = (int) Math.ceil( (double) count / pageSize);

        return new BoardsResDTO(null, pageable.getPageNumber(), totalPage, boards);
    }

    @Transactional(readOnly = true)
    public BoardsResDTO boardsWithKeyword(String type, String keyword, Pageable pageable) {
        List<BoardsDTO> boards = boardRepository.findAllByTypeAndTitleContaining(BoardType.valueOf(type), keyword, pageable).stream()
                .map(BoardsDTO::of)
                .toList();

        long count = boardRepository.countAllByTypeAndTitleContaining(BoardType.valueOf(type), keyword);
        int pageSize = pageable.getPageSize();
        int totalPage = (int) Math.ceil((double) count / pageSize);

        return new BoardsResDTO(keyword, pageable.getPageNumber(), totalPage, boards);
    }

    @Transactional(readOnly = true)
    public BoardResDTO board(String type, long id) {
        Board board = boardRepository.findByIdAndType(id, BoardType.valueOf(type));
        return BoardResDTO.of(board);
    }

    public void modifyBoard(long id, BoardModifyReqDTO reqDTO) {
        Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        board.modify(reqDTO.title(), reqDTO.content(), reqDTO.startDate(), reqDTO.endDate(), YNCode.valueOf(reqDTO.useYn()), YNCode.valueOf(reqDTO.deleteYn()));
    }

    public void deleteBoard(long id) {
        boardRepository.deleteById(id);
    }
}
