package com.mapmaker.service;

import com.mapmaker.domain.entity.Travel.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.Board.BoardLikeRepository;
import com.mapmaker.domain.repository.Gallery.GalleryLikeRepository;
import com.mapmaker.domain.repository.Travel.TravelLikeRepository;
import com.mapmaker.dto.Board.BoardDto;
import com.mapmaker.dto.Board.BoardLikeDto;
import com.mapmaker.dto.Gallery.GalleryDto;
import com.mapmaker.dto.Gallery.GalleryLikeDto;
import com.mapmaker.dto.Travel.TravelLikeDto;
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
    public Boolean isUserCheckedGalleryLike(UserEntity userEntity, GalleryDto galleryDto) {
        Long count = galleryLikeRepository.countByUserEntityAndGalleryEntity(userEntity, galleryDto.toEntity());
        return count > 0;
    }

    @Transactional
    public void saveGalleryLike(GalleryLikeDto galleryLikeDto) {
        galleryLikeRepository.save(galleryLikeDto.toEntity());
    }


    /****************
     * 게시판 좋아요 영역
     */
    @Transactional
    public Boolean isUserCheckedBoardLike(UserEntity userEntity, BoardDto boardDto) {
        Long count = boardLikeRepository.countByUserEntityAndBoardEntity(userEntity, boardDto.toEntity());
        return count > 0;
    }

    @Transactional
    public void saveBoardLike(BoardLikeDto boardLikeDto) {
        boardLikeRepository.save(boardLikeDto.toEntity());
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
    public void saveTravelLike(TravelLikeDto travelLikeDto) {
        travelLikeRepository.save(travelLikeDto.toEntity());
    }

}
