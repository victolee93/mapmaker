package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.GalleryCommentEntity;
import com.mapmaker.domain.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryCommentRepository extends JpaRepository<GalleryCommentEntity, Long> {
    List<GalleryCommentEntity> findByGalleryEntity(GalleryEntity galleryEntity);
}
