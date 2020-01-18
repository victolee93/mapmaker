package com.mapmaker.domain.repository.Travel;

import com.mapmaker.domain.entity.Travel.TravelCommentEntity;
import com.mapmaker.domain.entity.Travel.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelCommentRepository extends JpaRepository<TravelCommentEntity, Long> {
    List<TravelCommentEntity> findByTravelEntity(TravelEntity travelEntity);
}
