package com.mapmaker.service;

import com.mapmaker.domain.repository.GalleryCommentRepository;
import com.mapmaker.dto.GalleryCommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CommentService {
    private GalleryCommentRepository galleryCommentRepository;

    @Transactional
    public Long saveGalleryComment(GalleryCommentDto galleryCommentDto) {
        return galleryCommentRepository.save(galleryCommentDto.toEntity()).getId();
    }
}
