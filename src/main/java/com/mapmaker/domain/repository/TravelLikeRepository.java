package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.TravelLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelLikeRepository extends JpaRepository<TravelLikeEntity, Long> {
    Long countByUserEntityAndTravelEntity(UserEntity userEntity, TravelEntity travelEntity);
}
