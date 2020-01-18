package com.mapmaker.domain.repository.Gallery;

import com.mapmaker.domain.entity.Gallery.GalleryEntity;
import com.mapmaker.domain.entity.Gallery.GalleryLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryLikeRepository extends JpaRepository<GalleryLikeEntity, Long> {
    Long countByUserEntityAndGalleryEntity(UserEntity userEntity, GalleryEntity galleryEntity);
}
