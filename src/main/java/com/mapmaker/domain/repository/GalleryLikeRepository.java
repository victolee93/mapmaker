package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.GalleryLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryLikeRepository extends JpaRepository<GalleryLikeEntity, Long> {
    Long countByUserEntityAndGalleryEntity(UserEntity userEntity, GalleryEntity galleryEntity);

    Long countByGalleryEntity(GalleryEntity galleryEntity);
}
