package com.mapmaker.service;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.TravelRepository;
import com.mapmaker.dto.TravelDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TravelService {
    private TravelRepository travelRepository;

    @Transactional
    public TravelDto getTravelByUser(UserEntity userEntity){
        Optional<TravelEntity> travelEntityWrapper = travelRepository.findByUserEntity(userEntity);

        if (travelEntityWrapper.isEmpty() == true) {
            return null;
        }

        TravelEntity travelEntity = travelEntityWrapper.get();
        TravelDto travelDto = TravelDto.builder()
                .id(travelEntity.getId())
                .title(travelEntity.getTitle())
                .periodStartDate(travelEntity.getPeriodStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .periodEndDate(travelEntity.getPeriodEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .description(travelEntity.getDescription())
                .foodInfo(travelEntity.getFoodInfo())
                .placeInfo(travelEntity.getPlaceInfo())
                .lodgingInfo(travelEntity.getLodgingInfo())
                .transportInfo(travelEntity.getTransportInfo())
                .cost(travelEntity.getCost())
                .totalReview(travelEntity.getTotalReview())
                .memo(travelEntity.getMemo())
                .userEntity(userEntity)
                .openStatus(travelEntity.getOpenStatus())
                .build();

        return travelDto;
    }

    @Transactional
    public Long saveMap(TravelDto travelDto) {
        return travelRepository.save(travelDto.toEntity()).getId();
    }
}
