package com.mapmaker.domain.repository.Gallery;

import com.mapmaker.domain.entity.Gallery.GalleryCommentEntity;
import com.mapmaker.domain.entity.Gallery.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryCommentRepository extends JpaRepository<GalleryCommentEntity, Long> {
    List<GalleryCommentEntity> findByGalleryEntity(GalleryEntity galleryEntity);
}
