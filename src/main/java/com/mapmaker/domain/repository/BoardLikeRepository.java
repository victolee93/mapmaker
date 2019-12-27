package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.BoardEntity;
import com.mapmaker.domain.entity.BoardLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity, Long> {
    Long countByUserEntityAndBoardEntity(UserEntity userEntity, BoardEntity boardEntity);

}
