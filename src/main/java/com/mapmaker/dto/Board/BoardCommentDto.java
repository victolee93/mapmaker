package com.mapmaker.dto.Board;

import com.mapmaker.domain.entity.*;
import com.mapmaker.domain.entity.Board.BoardCommentEntity;
import com.mapmaker.domain.entity.Board.BoardEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardCommentDto {
    private Long id;
    private String content;
    private String username;
    private String date;

    private UserEntity userEntity;
    private BoardEntity boardEntity;

    public BoardCommentEntity toEntity() {
        return BoardCommentEntity.builder()
                .id(id)
                .content(content)
                .userEntity(userEntity)
                .boardEntity(boardEntity)
                .build();
    }

    @Builder
    public BoardCommentDto(Long id, String content, String username, String date) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.date = date;
    }
}
