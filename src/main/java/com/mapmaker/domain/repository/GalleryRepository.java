package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
}
