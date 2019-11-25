package com.mapmaker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.domain.repository.TravelRepository;
import com.mapmaker.dto.TravelDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TravelService {
    private TravelRepository travelRepository;
    private MarkerRepository markerRepository;

    @Transactional
    public List<TravelDto> getTravelListByUser(UserEntity userEntity){
        List<TravelEntity> travelEntityList = travelRepository.findAllByUserEntity(userEntity);
        List<TravelDto> travelDtoList = new ArrayList<>();

        if (travelEntityList.isEmpty()) {
            return travelDtoList;
        }

        for(TravelEntity travelEntity : travelEntityList) {
            TravelDto travelDto = convertDto(travelEntity, userEntity);
            travelDtoList.add(travelDto);
        }

        return travelDtoList;
    }

    @Transactional
    public String getTravelInfo(Long no){
        String travelInfoJson = "";
        Optional<MarkerEntity> markerEntityWrapper = markerRepository.findById(no);

        if (markerEntityWrapper.isEmpty() == true) {
            return travelInfoJson;
        }

        TravelEntity travelEntity = markerEntityWrapper.get().getTravelEntity();
        TravelDto travelDto = convertDto(travelEntity, null);

        ObjectMapper mapper = new ObjectMapper();
        try {
            travelInfoJson = mapper.writeValueAsString(travelDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return travelInfoJson;
    }

    @Transactional
    public Long saveMap(TravelDto travelDto) {
        return travelRepository.save(travelDto.toEntity()).getId();
    }

    private TravelDto convertDto(TravelEntity travelEntity, UserEntity userEntity) {
        return TravelDto.builder()
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
    }
}
