package com.mapmaker.domain.repository.Board;

import com.mapmaker.domain.entity.Board.BoardEntity;
import com.mapmaker.domain.entity.Board.BoardLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity, Long> {
    Long countByUserEntityAndBoardEntity(UserEntity userEntity, BoardEntity boardEntity);
}
