package com.mapmaker.domain.repository.Gallery;

import com.mapmaker.domain.entity.Gallery.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
}
