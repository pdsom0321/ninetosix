package com.ninetosix.coremodule.repository;

import com.ninetosix.coremodule.entity.Board;
import com.ninetosix.coremodule.vo.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByType(BoardType type);
    Board findByIdAndType(Long id, BoardType type);
}
