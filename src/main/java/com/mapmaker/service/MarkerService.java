package com.mapmaker.service;

import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.PositionsDto;
import com.mapmaker.dto.TravelDto;
import com.mapmaker.util.JsonManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MarkerService {
    private MarkerRepository markerRepository;

    @Transactional
    public List<MarkerDto> getMarkerList(List<TravelDto> travelDtoList){
        List<MarkerDto> markerList = new ArrayList<>();

        for(TravelDto travelDto : travelDtoList) {
            TravelEntity travelEntity = travelDto.toEntity();
            List<MarkerEntity> markerEntities = markerRepository.findAllByTravelEntity(travelEntity);

            if (markerEntities.isEmpty()) {
                continue;
            }

            for(MarkerEntity markerEntity : markerEntities) {
                markerList.add(convertEntityToDto(markerEntity, travelEntity));
            }
        }

        return markerList;
    }

    @Transactional
    public List<MarkerDto> getMarkerList(TravelDto travelDto){
        List<MarkerDto> markerList = new ArrayList<>();

        TravelEntity travelEntity = travelDto.toEntity();
        List<MarkerEntity> markerEntities = markerRepository.findAllByTravelEntity(travelEntity);

        if (markerEntities.isEmpty()) {
            return null;
        }

        for(MarkerEntity markerEntity : markerEntities) {
            markerList.add(convertEntityToDto(markerEntity, travelEntity));
        }

        return markerList;
    }

    @Transactional
    public boolean saveMaker(MarkerDto markerDto) {
        String positionsJson = markerDto.getMarkerPositions();

        List<PositionsDto> positionsDtoList = null;
        positionsDtoList = JsonManager.covnertJsonToDto(PositionsDto.class , positionsJson);

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

    private MarkerDto convertEntityToDto(MarkerEntity markerEntity, TravelEntity travelEntity) {
        return MarkerDto.builder()
                .id(markerEntity.getId())
                .latitude(markerEntity.getLatitude())
                .longitude(markerEntity.getLongitude())
                .travelEntity(travelEntity)
                .build();
    }

    public List<String> getPositions(List<MarkerDto> markerList) {
        List<String> positionsList = new ArrayList<>();
        for (MarkerDto markerDto : markerList) {
            PositionsDto positionsDto = PositionsDto.builder()
                    .id(markerDto.getId())
                    .latitude(markerDto.getLatitude())
                    .longitude(markerDto.getLongitude())
                    .build();
            positionsList.add(JsonManager.convertDtoToJson(positionsDto));
        }

        return positionsList;
    }
}
