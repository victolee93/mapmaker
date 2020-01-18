package com.mapmaker.service;

import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.Travel.TravelEntity;
import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.PositionsDto;
import com.mapmaker.dto.Travel.TravelDto;
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

    // 마커 리스트를 가져옴
    @Transactional
    public List<MarkerDto> getList(List<TravelDto> travelDtoList) {
        List<MarkerDto> markerList = new ArrayList<>();

        for (TravelDto travelDto : travelDtoList) {
            TravelEntity travelEntity = travelDto.toEntity();
            List<MarkerEntity> markerEntities = markerRepository.findAllByTravelEntity(travelEntity);

            if (markerEntities.isEmpty()) continue;

            for (MarkerEntity markerEntity : markerEntities) {
                markerList.add(convertEntityToDto(markerEntity, travelEntity));
            }
        }

        return markerList;
    }

    // 마커 리스트를 가져옴
    @Transactional
    public List<MarkerDto> getList(TravelDto travelDto) {
        List<MarkerDto> markerList = new ArrayList<>();

        TravelEntity travelEntity = travelDto.toEntity();
        List<MarkerEntity> markerEntities = markerRepository.findAllByTravelEntity(travelEntity);

        if (markerEntities.isEmpty()) return null;

        for (MarkerEntity markerEntity : markerEntities) {
            markerList.add(convertEntityToDto(markerEntity, travelEntity));
        }

        return markerList;
    }

    // 마커 정보를 저장
    @Transactional
    public boolean saveMaker(MarkerDto markerDto) {
        String positionsJson = markerDto.getMarkerPositions();

        List<PositionsDto> positionsDtoList = JsonManager.covnertJsonToDto(PositionsDto.class , positionsJson);

        if (positionsDtoList.isEmpty()) return false;

        for (PositionsDto positionsDto : positionsDtoList) {
            markerDto.setLatitude(positionsDto.getLatitude());
            markerDto.setLongitude(positionsDto.getLongitude());

            markerRepository.save(markerDto.toEntity());
        }

        return true;
    }

    // Json 포맷의 위치 정보들을 가져옴
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

    private MarkerDto convertEntityToDto(MarkerEntity markerEntity, TravelEntity travelEntity) {
        return MarkerDto.builder()
                .id(markerEntity.getId())
                .latitude(markerEntity.getLatitude())
                .longitude(markerEntity.getLongitude())
                .travelEntity(travelEntity)
                .build();
    }
}
