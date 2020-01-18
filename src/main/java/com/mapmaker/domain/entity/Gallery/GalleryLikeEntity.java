package com.mapmaker.domain.entity.Gallery;

import com.mapmaker.domain.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "gallery_like")
public class GalleryLikeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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
    public GalleryLikeEntity(Long id, GalleryEntity galleryEntity, UserEntity userEntity) {
        this.id = id;
        this.galleryEntity = galleryEntity;
        this.userEntity = userEntity;
    }
}
