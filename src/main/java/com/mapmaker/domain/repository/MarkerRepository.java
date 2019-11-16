package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.MarkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<MarkerEntity, Long> {
}
