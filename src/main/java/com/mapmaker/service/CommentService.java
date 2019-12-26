package com.mapmaker.service;

import com.mapmaker.domain.entity.GalleryCommentEntity;
import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.GalleryCommentRepository;
import com.mapmaker.dto.GalleryCommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {
    private GalleryCommentRepository galleryCommentRepository;

    @Transactional
    public List<GalleryCommentDto> getGalleryCommentList(GalleryEntity galleryEntity) {
        List<GalleryCommentDto> galleryCommentDtoList = new ArrayList<>();
        List<GalleryCommentEntity> galleryCommentEntityList = galleryCommentRepository.findByGalleryEntity(galleryEntity);

        for (GalleryCommentEntity galleryCommentEntity: galleryCommentEntityList) {
            galleryCommentDtoList.add(convertEntityToDto(galleryCommentEntity, galleryCommentEntity.getUserEntity()));
        }

        return galleryCommentDtoList;
    }

    @Transactional
    public Long saveGalleryComment(GalleryCommentDto galleryCommentDto) {
        return galleryCommentRepository.save(galleryCommentDto.toEntity()).getId();
    }

    private GalleryCommentDto convertEntityToDto (GalleryCommentEntity galleryCommentEntity, UserEntity userEntity) {
        return GalleryCommentDto.builder()
                .content(galleryCommentEntity.getContent())
                .username(userEntity.getNickname())
                .date(galleryCommentEntity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
