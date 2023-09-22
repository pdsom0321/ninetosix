package com.ninetosix.coremodule.repository;

import com.ninetosix.coremodule.entity.Board;
import com.ninetosix.coremodule.vo.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByType(BoardType type, Pageable pageable);

    Page<Board> findAllByTypeAndTitleContaining(BoardType type, String keyword, Pageable pageable);

    Board findByIdAndType(Long id, BoardType type);
}
