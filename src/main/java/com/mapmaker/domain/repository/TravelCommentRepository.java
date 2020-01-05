package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.TravelCommentEntity;
import com.mapmaker.domain.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelCommentRepository extends JpaRepository<TravelCommentEntity, Long> {
    List<TravelCommentEntity> findByTravelEntity(TravelEntity travelEntity);
}
