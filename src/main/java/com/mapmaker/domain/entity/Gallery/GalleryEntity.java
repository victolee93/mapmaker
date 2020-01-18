package com.mapmaker.domain.entity.Gallery;

import com.mapmaker.domain.entity.TimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "gallery")
public class GalleryEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String filePath;

    /*
     *  Relation Mapping
     */
    @OneToMany(mappedBy = "galleryEntity")
    private List<GalleryLikeEntity> galleryLikes = new ArrayList<>();

    @OneToMany(mappedBy = "galleryEntity")
    private List<GalleryCommentEntity> galleryComments = new ArrayList<>();


    @Builder
    public GalleryEntity(Long id, String content, String author, String filePath) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.filePath = filePath;
    }
}
