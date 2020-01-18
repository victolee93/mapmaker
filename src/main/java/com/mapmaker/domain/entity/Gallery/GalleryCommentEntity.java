package com.mapmaker.domain.entity.Gallery;

import com.mapmaker.domain.entity.TimeEntity;
import com.mapmaker.domain.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "gallery_comment")
public class GalleryCommentEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    /*
     *  Relation Mapping
     */
    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private GalleryEntity galleryEntity;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity userEntity;

    @Builder
    public GalleryCommentEntity(Long id, String content, GalleryEntity galleryEntity, UserEntity userEntity) {
        this.id = id;
        this.content = content;
        this.galleryEntity = galleryEntity;
        this.userEntity = userEntity;
    }
}
