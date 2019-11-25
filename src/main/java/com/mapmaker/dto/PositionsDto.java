package com.mapmaker.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PositionsDto {
    private Long id;
    private Double latitude;
    private Double longitude;

    @Builder
    public PositionsDto(Long id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
