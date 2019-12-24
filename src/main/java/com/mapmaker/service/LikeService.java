package com.mapmaker.service;

import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.GalleryLikeRepository;
import com.mapmaker.dto.GalleryLikeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class LikeService {
    private GalleryLikeRepository galleryLikeRepository;

    @Transactional
    public Long getCountGalleryLike(GalleryEntity galleryEntity) {
        return galleryLikeRepository.countByGalleryEntity(galleryEntity);
    }

    @Transactional
    public Boolean isUserCheckedGalleryLike(UserEntity userEntity, GalleryEntity galleryEntity) {
        Long count = galleryLikeRepository.countByUserEntityAndGalleryEntity(userEntity, galleryEntity);
        return count > 0;
    }

    @Transactional
    public Long saveGalleryLike(GalleryLikeDto galleryLikeDto) {
        return galleryLikeRepository.save(galleryLikeDto.toEntity()).getId();
    }
}
