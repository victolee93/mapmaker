package com.mapmaker.service;

import com.mapmaker.domain.entity.GalleryCommentEntity;
import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.repository.GalleryRepository;
import com.mapmaker.dto.GalleryCommentDto;
import com.mapmaker.dto.GalleryDto;
import com.mapmaker.util.JsonManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GalleryService {
    private CommentService commentService;
    private GalleryRepository galleryRepository;

    @Transactional
    public List<GalleryDto> getRecentList() {
        List<GalleryEntity> galleryEntities = galleryRepository.findAll();
        List<GalleryDto> galleryDtos = new ArrayList<>();

        for (GalleryEntity galleryEntity : galleryEntities) {
            galleryDtos.add(convertEntityToDto(galleryEntity, null));
        }

        return galleryDtos;
    }

    @Transactional
    public Long savePost(GalleryDto galleryDto) {
        return galleryRepository.save(galleryDto.toEntity()).getId();
    }

    @Transactional
    public GalleryEntity getGalleryInfo(Long no) {
        Optional<GalleryEntity> galleryEntityWrapper = galleryRepository.findById(no);
        return galleryEntityWrapper.get();
    }

    public String getGalleryInfoAndCommentsJson(Long no) {
        GalleryEntity galleryEntity = this.getGalleryInfo(no);
        List<GalleryCommentDto> galleryCommentList = commentService.getGalleryCommentList(galleryEntity);
        return JsonManager.convertDtoToJson(convertEntityToDto(galleryEntity, galleryCommentList));
    }

    private GalleryDto convertEntityToDto(GalleryEntity galleryEntity, List<GalleryCommentDto> galleryCommentList) {
        return GalleryDto.builder()
                .id(galleryEntity.getId())
                .title(galleryEntity.getTitle())
                .content(galleryEntity.getContent())
                .author(galleryEntity.getAuthor())
                .filePath(galleryEntity.getFilePath())
                .galleryCommentEntityList(galleryCommentList)
                .modifiedDate(galleryEntity.getModifiedDate())
                .build();
    }
}
