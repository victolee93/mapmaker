package com.mapmaker.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    /*
     *  Relation Mapping
     */
    @OneToMany(mappedBy = "boardEntity")
    private List<BoardLikeEntity> boardLikes = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity")
    private List<BoardCommentEntity> boardComments = new ArrayList<>();

    @Builder
    public BoardEntity(Long id, String title, String content, String author) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
