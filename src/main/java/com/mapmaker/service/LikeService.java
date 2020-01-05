package com.mapmaker.service;

import com.mapmaker.domain.entity.BoardEntity;
import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.BoardLikeRepository;
import com.mapmaker.domain.repository.GalleryLikeRepository;
import com.mapmaker.domain.repository.TravelLikeRepository;
import com.mapmaker.dto.BoardLikeDto;
import com.mapmaker.dto.GalleryLikeDto;
import com.mapmaker.dto.TravelLikeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class LikeService {
    private GalleryLikeRepository galleryLikeRepository;
    private BoardLikeRepository boardLikeRepository;
    private TravelLikeRepository travelLikeRepository;

    /****************
     * 갤러리 좋아요 영역
     */
    @Transactional
    public Boolean isUserCheckedGalleryLike(UserEntity userEntity, GalleryEntity galleryEntity) {
        Long count = galleryLikeRepository.countByUserEntityAndGalleryEntity(userEntity, galleryEntity);
        return count > 0;
    }

    @Transactional
    public Long saveGalleryLike(GalleryLikeDto galleryLikeDto) {
        return galleryLikeRepository.save(galleryLikeDto.toEntity()).getId();
    }


    /****************
     * 게시판 좋아요 영역
     */
    @Transactional
    public Boolean isUserCheckedBoardLike(UserEntity userEntity, BoardEntity boardEntity) {
        Long count = boardLikeRepository.countByUserEntityAndBoardEntity(userEntity, boardEntity);
        return count > 0;
    }

    @Transactional
    public Long saveBoardLike(BoardLikeDto boardLikeDto) {
        return boardLikeRepository.save(boardLikeDto.toEntity()).getId();
    }


    /****************
     * 여행 좋아요 영역
     */
    @Transactional
    public Boolean isUserCheckedTravelLike(UserEntity userEntity, TravelEntity travelEntity) {
        Long count = travelLikeRepository.countByUserEntityAndTravelEntity(userEntity, travelEntity);
        return count > 0;
    }

    @Transactional
    public Long saveTravelLike(TravelLikeDto travelLikeDto) {
        return travelLikeRepository.save(travelLikeDto.toEntity()).getId();
    }

}
