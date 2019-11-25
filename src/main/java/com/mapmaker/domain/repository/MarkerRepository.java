package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarkerRepository extends JpaRepository<MarkerEntity, Long> {
    List<MarkerEntity> findAllByTravelEntity(TravelEntity travelEntity);
}
