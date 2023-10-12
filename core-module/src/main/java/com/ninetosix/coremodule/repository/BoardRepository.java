package com.ninetosix.coremodule.repository;

import com.ninetosix.coremodule.entity.Board;
import com.ninetosix.coremodule.vo.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    long countAllByType(BoardType type);
    Page<Board> findAllByType(BoardType type, Pageable pageable);

    long countAllByTypeAndTitleContaining(BoardType type, String keyword);
    Page<Board> findAllByTypeAndTitleContaining(BoardType type, String keyword, Pageable pageable);

    Board findByIdAndType(long id, BoardType type);
}
