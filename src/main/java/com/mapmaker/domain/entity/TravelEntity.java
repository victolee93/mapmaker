package com.mapmaker.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "travel")
public class TravelEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime periodStartDate;

    @Column(nullable = false)
    private LocalDateTime periodEndDate;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(columnDefinition = "TEXT")
    private String foodInfo;

    @Column(columnDefinition = "TEXT")
    private String placeInfo;

    @Column(columnDefinition = "TEXT")
    private String lodgingInfo;

    @Column(columnDefinition = "TEXT")
    private String transportInfo;

    @Column(columnDefinition = "long")
    private Long cost;

    @Column(columnDefinition = "TEXT")
    private String totalReview;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Column(columnDefinition = "enum('public', 'private')", nullable=false)
    private String openStatus;

    /*
     *  Relation Mapping
     */
    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "travelEntity")
    private List<MarkerEntity> markers = new ArrayList<>();


    @Builder
    public TravelEntity(Long id, String title, LocalDateTime periodStartDate, LocalDateTime periodEndDate,
                        String description, String foodInfo, String placeInfo, String lodgingInfo,
                        String transportInfo, Long cost, String totalReview, String memo, String openStatus,
                        UserEntity userEntity) {
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
    }
}
