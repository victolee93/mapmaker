package com.mapmaker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.domain.repository.TravelRepository;
import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.PositionsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MarkerService {
    private MarkerRepository markerRepository;
    private TravelRepository travelRepository;

    @Transactional
    public List<MarkerDto> getMarkerList(UserEntity userEntity){
        List<MarkerDto> markerList = new ArrayList<>();
        List<TravelEntity> travelEntities = travelRepository.findAllByUserEntity(userEntity);

        if (travelEntities.isEmpty()) {
            return markerList;
        }

        for(TravelEntity travelEntity : travelEntities) {
            List<MarkerEntity> markerEntities = markerRepository.findAllByTravelEntity(travelEntity);

            if (markerEntities.isEmpty()) {
                return markerList;
            }

            for(MarkerEntity markerEntity : markerEntities) {
                markerList.add(convertDto(markerEntity, travelEntity));
            }
        }

        return markerList;
    }

    @Transactional
    public boolean saveMaker(MarkerDto markerDto) {
        // convert : 위도 경도 Json -> PositionsDto
        ObjectMapper mapper = new ObjectMapper();
        List<PositionsDto> positionsDtoList = null;
        try {
            String positionsJson = markerDto.getMarkerPositions();
            positionsDtoList = mapper.readValue(positionsJson, new TypeReference<List<PositionsDto>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (positionsDtoList == null) {
            return false;
        }

        // save
        for (PositionsDto positionsDto : positionsDtoList) {
            markerDto.setLatitude(positionsDto.getLatitude());
            markerDto.setLongitude(positionsDto.getLongitude());
            markerRepository.save(markerDto.toEntity());
        }

        return true;
    }

    private MarkerDto convertDto(MarkerEntity markerEntity, TravelEntity travelEntity) {
        return MarkerDto.builder()
                .id(markerEntity.getId())
                .latitude(markerEntity.getLatitude())
                .longitude(markerEntity.getLongitude())
                .travelEntity(travelEntity)
                .build();
    }

    public List<String> getPositions(List<MarkerDto> markerList) {
        if (markerList.isEmpty()) {
            return null;
        }

        List<String> positionsList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (MarkerDto markerDto : markerList) {
            try {
                PositionsDto positionsDto = PositionsDto.builder()
                        .latitude(markerDto.getLatitude())
                        .longitude(markerDto.getLongitude())
                        .build();
                positionsList.add(mapper.writeValueAsString(positionsDto));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return positionsList;
    }
}
