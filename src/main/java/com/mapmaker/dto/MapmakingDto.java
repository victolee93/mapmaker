package com.mapmaker.dto;

import com.mapmaker.domain.entity.Travel.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.Travel.TravelDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MapmakingDto {
    private Long id;
    private String title;
    private String periodStartDate;
    private String periodEndDate;
    private String description;
    private String foodInfo;
    private String placeInfo;
    private String lodgingInfo;
    private String transportInfo;
    private Long cost;
    private String totalReview;
    private String memo;
    private String openStatus;
    private UserEntity userEntity;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private String markerPositions;
    private TravelEntity travelEntity;

    private TravelDto travelDto;
    private MarkerDto markerDto;


    @Builder
    public MapmakingDto(Long id, String title, String periodStartDate, String periodEndDate,
                     String description, String foodInfo, String placeInfo, String lodgingInfo,
                     String transportInfo, Long cost, String totalReview, String memo, String openStatus,
                     UserEntity userEntity, LocalDateTime createdDate, LocalDateTime modifiedDate,
                     String markerPositions, TravelEntity travelEntity) {

        this.id = id;
        this.title = title;
        this.periodStartDate = periodStartDate;
        this.periodEndDate = periodEndDate;
        this.description = description;
        this.foodInfo = foodInfo;
        this.placeInfo = placeInfo;
        this.lodgingInfo = lodgingInfo;
        this.transportInfo = transportInfo;
        this.cost = cost;
        this.totalReview = totalReview;
        this.memo = memo;
        this.openStatus = openStatus;
        this.userEntity = userEntity;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

        this.markerPositions = markerPositions;
        this.travelEntity = travelEntity;
    }

    public void setTravelDto(MapmakingDto mapmakingDto) {
        this.travelDto = TravelDto.builder()
                .id(mapmakingDto.getId())
                .title(mapmakingDto.getTitle())
                .periodStartDate(mapmakingDto.getPeriodStartDate())
                .periodEndDate(mapmakingDto.getPeriodEndDate())
                .description(mapmakingDto.getDescription())
                .foodInfo(mapmakingDto.getFoodInfo())
                .placeInfo(mapmakingDto.getPlaceInfo())
                .lodgingInfo(mapmakingDto.getLodgingInfo())
                .transportInfo(mapmakingDto.getTransportInfo())
                .cost(mapmakingDto.getCost())
                .totalReview(mapmakingDto.getTotalReview())
                .memo(mapmakingDto.getMemo())
                .openStatus(mapmakingDto.getOpenStatus())
                .userEntity(mapmakingDto.getUserEntity())
                .build();
    }

    public TravelDto getTravelDto() {
        return this.travelDto;
    }

    public void setMarkerDto(MapmakingDto mapmakingDto) {
        this.markerDto = MarkerDto.builder()
                .id(mapmakingDto.getId())
                .markerPositions(mapmakingDto.getMarkerPositions())
                .travelEntity(travelEntity)
                .build();
    }

    public MarkerDto getMarkerDto() {
        return this.markerDto;
    }
}
