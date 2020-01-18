package com.mapmaker.dto.Travel;

import com.mapmaker.domain.entity.*;
import com.mapmaker.domain.entity.Travel.TravelEntity;
import com.mapmaker.domain.entity.Travel.TravelLikeEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TravelLikeDto {
    private Long id;
    private UserEntity userEntity;
    private TravelEntity travelEntity;

    public TravelLikeEntity toEntity() {
        return TravelLikeEntity.builder()
                .id(id)
                .userEntity(userEntity)
                .travelEntity(travelEntity)
                .build();
    }

    @Builder
    public TravelLikeDto(Long id, UserEntity userEntity, TravelEntity travelEntity) {
        this.id = id;
        this.userEntity = userEntity;
        this.travelEntity = travelEntity;
    }
}
