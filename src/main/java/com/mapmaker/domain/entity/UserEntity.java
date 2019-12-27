package com.mapmaker.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class UserEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nickname;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    /*
     *  Relation Mapping
     */
    @OneToMany(mappedBy = "userEntity") // 여행정보
    private List<TravelEntity> travels = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity") // 갤러리 좋아요
    private List<GalleryLikeEntity> galleryLikes = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity") // 갤러리 댓글
    private List<GalleryCommentEntity> galleryComments = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity") // 게시판 좋아요
    private List<BoardLikeEntity> boardLikes = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity") // 게시판 댓글
    private List<BoardCommentEntity> boardComments = new ArrayList<>();

    @Builder
    public UserEntity(Long id, String nickname, String email, String password) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}