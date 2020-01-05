package com.mapmaker.dto;

import com.mapmaker.domain.entity.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TravelCommentDto {
    private Long id;
    private String content;
    private String username;
    private String date;

    private UserEntity userEntity;
    private TravelEntity travelEntity;

    public TravelCommentEntity toEntity(){
        return TravelCommentEntity.builder()
                .id(id)
                .content(content)
                .userEntity(userEntity)
                .travelEntity(travelEntity)
                .build();
    }

    @Builder
    public TravelCommentDto(Long id, String content, String username, String date) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.date = date;
    }
}
