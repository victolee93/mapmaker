package com.mapmaker.dto;

import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.TravelEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MarkerDto {
    private Long id;
    private String markerPositions;
    private Double latitude;
    private Double longitude;
    private TravelEntity travelEntity;

    public MarkerEntity toEntity(){
        return MarkerEntity.builder()
                .id(id)
                .latitude(latitude)
                .longitude(longitude)
                .travelEntity(travelEntity)
                .build();
    }

    @Builder
    public MarkerDto(Long id, String markerPositions, Double latitude, Double longitude, TravelEntity travelEntity) {
        this.id = id;
        this.markerPositions = markerPositions;
        this.latitude = latitude;
        this.longitude = longitude;
        this.travelEntity = travelEntity;
    }
}
