package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.BoardCommentEntity;
import com.mapmaker.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {
    List<BoardCommentEntity> findByBoardEntity(BoardEntity galleryEntity);
}
