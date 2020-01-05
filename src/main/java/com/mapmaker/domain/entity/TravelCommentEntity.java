package com.mapmaker.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "travel_comment")
public class TravelCommentEntity extends TimeEntity  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

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
    public TravelCommentEntity(Long id, String content, TravelEntity travelEntity, UserEntity userEntity) {
        this.id = id;
        this.content = content;
        this.travelEntity = travelEntity;
        this.userEntity = userEntity;
    }
}
