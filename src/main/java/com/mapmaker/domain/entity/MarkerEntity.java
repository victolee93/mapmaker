package com.mapmaker.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "marker")
public class MarkerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    /*
     *  Relation Mapping
     */
    @ManyToOne
    @JoinColumn(name = "travel_id")
    private TravelEntity travelEntity;

    @Builder
    public MarkerEntity(Long id, Double latitude, Double longitude, TravelEntity travelEntity) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.travelEntity = travelEntity;
    }
}
