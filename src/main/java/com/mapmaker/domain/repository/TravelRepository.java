package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {
}
