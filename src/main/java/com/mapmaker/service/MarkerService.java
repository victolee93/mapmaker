package com.mapmaker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.PositionsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MarkerService {
    private MarkerRepository markerRepository;

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
}
