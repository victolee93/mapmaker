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
    @OneToMany(mappedBy = "userEntity")
    private List<TravelEntity> travels = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<GalleryLikeEntity> galleryLikes = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<GalleryCommentEntity> galleryComments = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<BoardCommentEntity> boardComments = new ArrayList<>();

    @Builder
    public UserEntity(Long id, String nickname, String email, String password) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}