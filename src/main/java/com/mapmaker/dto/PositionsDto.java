package com.mapmaker.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PositionsDto {
    private Double latitude;
    private Double longitude;

    @Builder
    public PositionsDto(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
