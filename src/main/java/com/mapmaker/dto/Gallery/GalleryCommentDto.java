package com.mapmaker.dto.Gallery;

import com.mapmaker.domain.entity.Gallery.GalleryCommentEntity;
import com.mapmaker.domain.entity.Gallery.GalleryEntity;
import com.mapmaker.domain.entity.UserEntity;
import lombok.*;

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

    public GalleryCommentEntity toEntity() {
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
