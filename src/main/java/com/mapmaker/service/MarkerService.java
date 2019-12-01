package com.mapmaker.service;

import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.domain.repository.TravelRepository;
import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.PositionsDto;
import com.mapmaker.util.JasonManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String positionsJson = markerDto.getMarkerPositions();

        List<PositionsDto> positionsDtoList = null;
        positionsDtoList = JasonManager.covnertJsonToDto(PositionsDto.class , positionsJson);

        if (positionsDtoList.isEmpty()) {
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
        for (MarkerDto markerDto : markerList) {
            PositionsDto positionsDto = PositionsDto.builder()
                    .id(markerDto.getId())
                    .latitude(markerDto.getLatitude())
                    .longitude(markerDto.getLongitude())
                    .build();
            positionsList.add(JasonManager.convertDtoToJsonString(positionsDto));
        }

        return positionsList;
    }
}
