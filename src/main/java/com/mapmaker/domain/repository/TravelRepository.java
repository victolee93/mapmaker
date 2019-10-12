package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {
    Optional<TravelEntity> findByUserEntity(UserEntity userEntity);
}
