package com.mapmaker.dto;

import com.mapmaker.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private Integer likeCount;
    private Integer commentCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public BoardDto(Long id, String title, String content, String author,
                    Integer likeCount, Integer commentCount,
                    LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}