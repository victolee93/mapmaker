package com.mapmaker.dto;

import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.GalleryLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryLikeDto {
    private Long id;
    private UserEntity userEntity;
    private GalleryEntity galleryEntity;

    public GalleryLikeEntity toEntity(){
        return GalleryLikeEntity.builder()
                .id(id)
                .userEntity(userEntity)
                .galleryEntity(galleryEntity)
                .build();
    }

    @Builder
    public GalleryLikeDto(Long id, UserEntity userEntity, GalleryEntity galleryEntity) {
        this.id = id;
        this.userEntity = userEntity;
        this.galleryEntity = galleryEntity;
    }
}
