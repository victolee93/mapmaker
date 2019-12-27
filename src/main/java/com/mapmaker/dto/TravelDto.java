package com.mapmaker.dto;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TravelDto {
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
    private String filePath;
    private String userName;

    private UserEntity userEntity;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public TravelEntity toEntity(){
        // 여행일정 : LocalDateTime으로 변환
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(periodStartDate, dateformatter);
        LocalDate endDate = LocalDate.parse(periodEndDate, dateformatter);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atStartOfDay();

        return TravelEntity.builder()
                .id(id)
                .title(title)
                .periodStartDate(startDateTime)
                .periodEndDate(endDateTime)
                .description(description)
                .foodInfo(foodInfo)
                .placeInfo(placeInfo)
                .lodgingInfo(lodgingInfo)
                .transportInfo(transportInfo)
                .cost(cost)
                .totalReview(totalReview)
                .memo(memo)
                .openStatus(openStatus)
                .filePath(filePath)
                .userEntity(userEntity)
                .build();
    }

    @Builder
    public TravelDto(Long id, String title, String periodStartDate, String periodEndDate,
                     String description, String foodInfo, String placeInfo, String lodgingInfo,
                     String transportInfo, Long cost, String totalReview, String memo, String openStatus,
                     String userName, String filePath,
                     UserEntity userEntity, LocalDateTime createdDate, LocalDateTime modifiedDate) {
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
        this.filePath = filePath;
        this.userName = userName;
        this.userEntity = userEntity;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
