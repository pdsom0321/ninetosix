package com.ninetosix.adminmodule.nts.board.service;

import com.ninetosix.adminmodule.nts.board.dto.BoardModifyReqDTO;
import com.ninetosix.adminmodule.nts.board.dto.BoardReqDTO;
import com.ninetosix.adminmodule.nts.board.dto.BoardResDTO;
import com.ninetosix.adminmodule.nts.board.dto.BoardsResDTO;
import com.ninetosix.coremodule.entity.Board;
import com.ninetosix.coremodule.repository.BoardRepository;
import com.ninetosix.coremodule.vo.BoardType;
import com.ninetosix.coremodule.vo.YNCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public void createBoard(String type, BoardReqDTO reqDTO) {
        boardRepository.save(Board.create(BoardType.valueOf(type), reqDTO.title(), reqDTO.content(), reqDTO.startDate(), reqDTO.endDate(), reqDTO.useYn(), reqDTO.deleteYn()));
    }

    public List<BoardsResDTO> boards(String type) {
        return boardRepository.findAllByType(BoardType.valueOf(type)).stream()
                .map(BoardsResDTO::of)
                .collect(Collectors.toList());
    }

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
