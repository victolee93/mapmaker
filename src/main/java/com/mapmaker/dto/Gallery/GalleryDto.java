package com.mapmaker.dto.Gallery;

import com.mapmaker.domain.entity.Gallery.GalleryEntity;
import com.mapmaker.dto.Gallery.GalleryCommentDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {
    private Long id;
    private String author;
    private String content;
    private String filePath;
    private Integer likeCount;
    private Integer commentCount;
    private Boolean likeChecked;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private List<GalleryCommentDto> galleryCommentEntities;

    public GalleryEntity toEntity() {
        return GalleryEntity.builder()
                .id(id)
                .author(author)
                .content(content)
                .filePath(filePath)
                .build();
    }

    @Builder
    public GalleryDto(Long id, String content, String author, String filePath,
                      Integer likeCount, Integer commentCount, Boolean likeChecked, List<GalleryCommentDto> galleryCommentEntities,
                    LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.filePath = filePath;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.likeChecked = likeChecked;
        this.galleryCommentEntities = galleryCommentEntities;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
