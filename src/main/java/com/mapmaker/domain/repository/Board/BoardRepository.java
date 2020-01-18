package com.mapmaker.domain.repository.Board;

import com.mapmaker.domain.entity.Board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
