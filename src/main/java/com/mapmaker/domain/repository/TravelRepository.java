package com.mapmaker.domain.repository;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {
    List<TravelEntity> findAllByUserEntity(UserEntity userEntity);
}
