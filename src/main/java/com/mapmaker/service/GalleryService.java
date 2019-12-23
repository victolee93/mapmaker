package com.mapmaker.service;

import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.repository.GalleryRepository;
import com.mapmaker.dto.GalleryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class GalleryService {
    private GalleryRepository galleryRepository;

    @Transactional
    public List<GalleryDto> getRecentList() {
        List<GalleryEntity> galleryEntities = galleryRepository.findAll();
        List<GalleryDto> galleryDtos = new ArrayList<>();

        for (GalleryEntity galleryEntity : galleryEntities) {
            galleryDtos.add(convertEntityToDto(galleryEntity));
        }

        return galleryDtos;
    }

    @Transactional
    public Long savePost(GalleryDto galleryDto) {
        return galleryRepository.save(galleryDto.toEntity()).getId();
    }

    private GalleryDto convertEntityToDto(GalleryEntity galleryEntity) {
        return GalleryDto.builder()
                .id(galleryEntity.getId())
                .title(galleryEntity.getTitle())
                .content(galleryEntity.getContent())
                .author(galleryEntity.getAuthor())
                .filePath(galleryEntity.getFilePath())
                .modifiedDate(galleryEntity.getModifiedDate())
                .build();
    }
}
