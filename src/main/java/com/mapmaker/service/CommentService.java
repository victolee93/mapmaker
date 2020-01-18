package com.mapmaker.service;

import com.mapmaker.domain.entity.*;
import com.mapmaker.domain.entity.Board.BoardCommentEntity;
import com.mapmaker.domain.entity.Gallery.GalleryCommentEntity;
import com.mapmaker.domain.entity.Gallery.GalleryEntity;
import com.mapmaker.domain.entity.Travel.TravelCommentEntity;
import com.mapmaker.domain.repository.Board.BoardCommentRepository;
import com.mapmaker.domain.repository.Gallery.GalleryCommentRepository;
import com.mapmaker.domain.repository.Travel.TravelCommentRepository;
import com.mapmaker.dto.Board.BoardCommentDto;
import com.mapmaker.dto.Board.BoardDto;
import com.mapmaker.dto.Gallery.GalleryCommentDto;
import com.mapmaker.dto.Travel.TravelCommentDto;
import com.mapmaker.dto.Travel.TravelDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {
    private GalleryCommentRepository galleryCommentRepository;
    private BoardCommentRepository boardCommentRepository;
    private TravelCommentRepository travelCommentRepository;

    /****************
     * 갤러리 댓글 영역
     */
    @Transactional
    public List<GalleryCommentDto> getGalleryCommentList(GalleryEntity galleryEntity) {
        List<GalleryCommentDto> galleryCommentDtoList = new ArrayList<>();
        List<GalleryCommentEntity> galleryCommentEntities = galleryCommentRepository.findByGalleryEntity(galleryEntity);

        for (GalleryCommentEntity galleryCommentEntity: galleryCommentEntities) {
            galleryCommentDtoList.add(convertEntityToGalleryCommentDto(galleryCommentEntity, galleryCommentEntity.getUserEntity()));
        }

        return galleryCommentDtoList;
    }

    @Transactional
    public void saveGalleryComment(GalleryCommentDto galleryCommentDto) {
        galleryCommentRepository.save(galleryCommentDto.toEntity());
    }


    /****************
     * 게시판 댓글 영역
     */
    @Transactional
    public List<BoardCommentDto> getGalleryCommentList(BoardDto boardDto) {
        List<BoardCommentDto> boardCommentDtoList = new ArrayList<>();
        List<BoardCommentEntity> boardCommentEntities = boardCommentRepository.findByBoardEntity(boardDto.toEntity());

        for (BoardCommentEntity boardCommentEntity: boardCommentEntities) {
            boardCommentDtoList.add(convertEntityToBoardCommentDto(boardCommentEntity, boardCommentEntity.getUserEntity()));
        }

        return boardCommentDtoList;
    }

    @Transactional
    public void saveBoardComment(BoardCommentDto boardCommentDto) {
        boardCommentRepository.save(boardCommentDto.toEntity());
    }

    /****************
     * 여행 댓글 영역
     */
    @Transactional
    public List<TravelCommentDto> getTravelCommentList(TravelDto travelDto) {
        List<TravelCommentDto> travelCommentDtoList = new ArrayList<>();
        List<TravelCommentEntity> travelCommentEntities = travelCommentRepository.findByTravelEntity(travelDto.toEntity());

        for (TravelCommentEntity travelCommentEntity: travelCommentEntities) {
            travelCommentDtoList.add(convertEntityToTravelCommentDto(travelCommentEntity, travelCommentEntity.getUserEntity()));
        }

        return travelCommentDtoList;
    }

    @Transactional
    public void saveTravelComment(TravelCommentDto travelCommentDto) {
        travelCommentRepository.save(travelCommentDto.toEntity());
    }


    /****************
     * DTO 변환 영역
     */
    private GalleryCommentDto convertEntityToGalleryCommentDto (GalleryCommentEntity galleryCommentEntity, UserEntity userEntity) {
        return GalleryCommentDto.builder()
                .content(galleryCommentEntity.getContent())
                .username(userEntity.getNickname())
                .date(galleryCommentEntity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    private BoardCommentDto convertEntityToBoardCommentDto (BoardCommentEntity boardCommentEntity, UserEntity userEntity) {
        return BoardCommentDto.builder()
                .content(boardCommentEntity.getContent())
                .username(userEntity.getNickname())
                .date(boardCommentEntity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    private TravelCommentDto convertEntityToTravelCommentDto (TravelCommentEntity travelCommentEntity, UserEntity userEntity) {
        return TravelCommentDto.builder()
                .content(travelCommentEntity.getContent())
                .username(userEntity.getNickname())
                .date(travelCommentEntity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
