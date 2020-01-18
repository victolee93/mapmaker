package com.mapmaker.domain.entity.Travel;

import com.mapmaker.domain.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "travel_like")
public class TravelLikeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*
     *  Relation Mapping
     */
    @ManyToOne
    @JoinColumn(name = "travel_id")
    private TravelEntity travelEntity;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity userEntity;

    @Builder
    public TravelLikeEntity(Long id, TravelEntity travelEntity, UserEntity userEntity) {
        this.id = id;
        this.travelEntity = travelEntity;
        this.userEntity = userEntity;
    }
}
