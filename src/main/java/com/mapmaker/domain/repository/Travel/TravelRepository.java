package com.mapmaker.domain.repository.Travel;

import com.mapmaker.domain.entity.Travel.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {
    List<TravelEntity> findAllByUserEntity(UserEntity userEntity);

    List<TravelEntity> findByTitleContaining(String keyword);

    @Query(value =
            "SELECT t, count(t) as like_count " +
            "FROM TravelEntity t " +
                "LEFT JOIN t.travelLikes tl " +
            "GROUP BY t.id " +
            "ORDER BY like_count DESC"
    )
    List<TravelEntity> findByTravelLikesOrderByDesc();
}
