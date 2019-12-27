package com.mapmaker.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board_like")
public class BoardLikeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*
     *  Relation Mapping
     */
    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity userEntity;

    @Builder
    public BoardLikeEntity(Long id, BoardEntity boardEntity, UserEntity userEntity) {
        this.id = id;
        this.boardEntity = boardEntity;
        this.userEntity = userEntity;
    }
}
