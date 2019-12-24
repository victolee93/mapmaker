package com.mapmaker.dto;

import com.mapmaker.domain.entity.GalleryCommentEntity;
import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryCommentDto {
    private Long id;
    private String content;
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
    public GalleryCommentDto(Long id, String content, UserEntity userEntity, GalleryEntity galleryEntity) {
        this.id = id;
        this.content = content;
        this.userEntity = userEntity;
        this.galleryEntity = galleryEntity;
    }
}
