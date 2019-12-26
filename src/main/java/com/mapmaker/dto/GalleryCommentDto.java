package com.mapmaker.dto;

import com.mapmaker.domain.entity.GalleryCommentEntity;
import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryCommentDto {
    private Long id;
    private String content;
    private String username;
    private String date;

    private UserEntity userEntity;
    private GalleryEntity galleryEntity;

    public GalleryCommentEntity toEntity(){
        return GalleryCommentEntity.builder()
                .id(id)
                .content(content)
                .userEntity(userEntity)
                .galleryEntity(galleryEntity)
                .build();
    }

    @Builder
    public GalleryCommentDto(Long id, String content, String username, String date) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.date = date;
    }
}
