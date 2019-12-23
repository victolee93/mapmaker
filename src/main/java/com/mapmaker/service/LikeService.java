package com.mapmaker.service;

import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.GalleryLikeEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.LikeRepository;
import com.mapmaker.dto.GalleryLikeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class LikeService {
    private LikeRepository likeRepository;

    @Transactional
    public Long getCountGalleryLike(GalleryEntity galleryEntity) {
        return likeRepository.countByGalleryEntity(galleryEntity);
    }

    @Transactional
    public Boolean isUserCheckedGalleryLike(UserEntity userEntity, GalleryEntity galleryEntity) {
        Long count = likeRepository.countByUserEntityAndGalleryEntity(userEntity, galleryEntity);
        System.out.println(count);
        return count > 0;
    }

    @Transactional
    public Long saveGalleryLike(GalleryLikeDto galleryLikeDto) {
        return likeRepository.save(galleryLikeDto.toEntity()).getId();
    }
}
