package com.mapmaker.domain.repository.Travel;

import com.mapmaker.domain.entity.Travel.TravelEntity;
import com.mapmaker.domain.entity.Travel.TravelLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelLikeRepository extends JpaRepository<TravelLikeEntity, Long> {
    Long countByUserEntityAndTravelEntity(UserEntity userEntity, TravelEntity travelEntity);
}
