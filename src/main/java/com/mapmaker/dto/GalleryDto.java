package com.mapmaker.dto;

import com.mapmaker.domain.entity.GalleryCommentEntity;
import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.GalleryLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private String filePath;
    private Long totalLike;
    private Boolean checked;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private UserEntity userEntity;
    private GalleryLikeEntity galleryLikeEntity;

    private List<GalleryCommentDto> galleryCommentEntityList;

    public GalleryEntity toEntity(){
        return GalleryEntity.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .filePath(filePath)
                .build();
    }

    @Builder
    public GalleryDto(Long id, String title, String content, String author, String filePath,
                    Long totalLike, Boolean checked, List<GalleryCommentDto> galleryCommentEntityList,
                    LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.totalLike = totalLike;
        this.checked = checked;
        this.galleryCommentEntityList = galleryCommentEntityList;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
