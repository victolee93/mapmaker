package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.GalleryCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryCommentRepository extends JpaRepository<GalleryCommentEntity, Long> {
}
