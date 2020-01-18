package com.mapmaker.domain.repository.Board;

import com.mapmaker.domain.entity.Board.BoardCommentEntity;
import com.mapmaker.domain.entity.Board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {
    List<BoardCommentEntity> findByBoardEntity(BoardEntity galleryEntity);
}
