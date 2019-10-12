package com.mapmaker.service;

import com.mapmaker.domain.repository.TravelRepository;
import com.mapmaker.dto.TravelDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class TravelService {
    private TravelRepository travelRepository;

    @Transactional
    public Long saveMap(TravelDto travelDto) {
        return travelRepository.save(travelDto.toEntity()).getId();
    }
}
