package com.mapmaker.dto;

import com.mapmaker.domain.entity.BoardEntity;
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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public GalleryEntity toEntity(){
        return GalleryEntity.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public GalleryDto(Long id, String title, String content, String author,
                    LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
