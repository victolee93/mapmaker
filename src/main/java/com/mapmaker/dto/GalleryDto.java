package com.mapmaker.dto;

import com.mapmaker.domain.entity.GalleryEntity;
import lombok.*;

import java.time.LocalDateTime;

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
                    Long totalLike, Boolean checked,
                    LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.totalLike = totalLike;
        this.checked = checked;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
