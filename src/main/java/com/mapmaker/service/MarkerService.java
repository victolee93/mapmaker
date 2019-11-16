package com.mapmaker.service;

import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.dto.MarkerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MarkerService {
    private MarkerRepository markerRepository;

    @Transactional
    public Long saveMaker(MarkerDto markerDto) {
        // TODO markerPositions를 String이 아닌, Serialize 또는 json으로 가져올까??
        // MarkerDto(id=null, markerPositions=(37.41532125866593, 126.9895171095679),(37.4274299123406, 127.01879993382289),(37.404364299978134, 127.01554133247976), latitude=null, longitude=null, travelEntity=com.mapmaker.domain.entity.TravelEntity@4bbabe4e)

        // TODO 파싱 및 save 로직 구현
        for () {
            // 1. markerDto의 markerPositions를 파싱해서 latitude, longitude 쌍의 개수만큼 반복문 돌린다.

            // 2. 반복문 내부에서는 dto를 latitude, longitude 값을 할당하여 그 dto로 repo.save() 호출
            markerRepository.save(markerDto.toEntity()).getId();
        }
    }
}
