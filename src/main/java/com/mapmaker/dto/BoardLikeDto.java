package com.mapmaker.dto;

import com.mapmaker.domain.entity.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardLikeDto {
    private Long id;
    private UserEntity userEntity;
    private BoardEntity boardEntity;

    public BoardLikeEntity toEntity(){
        return BoardLikeEntity.builder()
                .id(id)
                .userEntity(userEntity)
                .boardEntity(boardEntity)
                .build();
    }

    @Builder
    public BoardLikeDto(Long id, UserEntity userEntity, BoardEntity boardEntity) {
        this.id = id;
        this.userEntity = userEntity;
        this.boardEntity = boardEntity;
    }
}
