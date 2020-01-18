package com.mapmaker.service;

import com.mapmaker.domain.entity.Gallery.GalleryEntity;
import com.mapmaker.domain.repository.Gallery.GalleryRepository;
import com.mapmaker.dto.Gallery.GalleryCommentDto;
import com.mapmaker.dto.Gallery.GalleryDto;
import com.mapmaker.util.JsonManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class GalleryService {
    private CommentService commentService;
    private GalleryRepository galleryRepository;

    // 최근 갤러리 목록
    @Transactional
    public List<GalleryDto> getRecentList() {
        List<GalleryEntity> galleryEntities = galleryRepository.findAll();
        List<GalleryDto> galleryDtoList = new ArrayList<>();

        for (GalleryEntity galleryEntity : galleryEntities) {
            GalleryDto galleryDto = convertEntityToDto(galleryEntity, null);

            galleryDto.setLikeCount(galleryEntity.getGalleryLikes().size());
            galleryDto.setCommentCount(galleryEntity.getGalleryComments().size());

            galleryDtoList.add(galleryDto);
        }

        return galleryDtoList;
    }

    // 갤러리 글 등록
    @Transactional
    public void savePost(GalleryDto galleryDto) {
        galleryRepository.save(galleryDto.toEntity());
    }

    // 갤러리 상세정보
    @Transactional
    public GalleryEntity getDetail(Long no) {
        return galleryRepository.findById(no).get();
    }

    // 갤러리 상세정보와 코멘트를 Json으로 반환
    @Transactional
    public String getDetailAndCommentsJson(Long no) {
        GalleryEntity galleryEntity = this.getDetail(no);
        List<GalleryCommentDto> galleryCommentList = commentService.getGalleryCommentList(galleryEntity);

        return JsonManager.convertDtoToJson(convertEntityToDto(galleryEntity, galleryCommentList));
    }

    private GalleryDto convertEntityToDto(GalleryEntity galleryEntity, List<GalleryCommentDto> galleryCommentList) {
        return GalleryDto.builder()
                .id(galleryEntity.getId())
                .content(galleryEntity.getContent())
                .author(galleryEntity.getAuthor())
                .filePath(galleryEntity.getFilePath())
                .galleryCommentEntities(galleryCommentList)
                .modifiedDate(galleryEntity.getModifiedDate())
                .build();
    }
}
