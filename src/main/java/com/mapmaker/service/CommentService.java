package com.mapmaker.service;

import com.mapmaker.domain.entity.*;
import com.mapmaker.domain.repository.BoardCommentRepository;
import com.mapmaker.domain.repository.GalleryCommentRepository;
import com.mapmaker.dto.BoardCommentDto;
import com.mapmaker.dto.GalleryCommentDto;
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

    /****************
     * 갤러리 댓글 영역
     */
    @Transactional
    public List<GalleryCommentDto> getGalleryCommentList(GalleryEntity galleryEntity) {
        List<GalleryCommentDto> galleryCommentDtoList = new ArrayList<>();
        List<GalleryCommentEntity> galleryCommentEntityList = galleryCommentRepository.findByGalleryEntity(galleryEntity);

        for (GalleryCommentEntity galleryCommentEntity: galleryCommentEntityList) {
            galleryCommentDtoList.add(convertEntityToGalleryCommentDto(galleryCommentEntity, galleryCommentEntity.getUserEntity()));
        }

        return galleryCommentDtoList;
    }

    @Transactional
    public Long saveGalleryComment(GalleryCommentDto galleryCommentDto) {
        return galleryCommentRepository.save(galleryCommentDto.toEntity()).getId();
    }


    /****************
     * 게시판 댓글 영역
     */
    @Transactional
    public List<BoardCommentDto> getGalleryCommentList(BoardEntity boardEntity) {
        List<BoardCommentDto> boardCommentDtoList = new ArrayList<>();
        List<BoardCommentEntity> boardCommentEntityList = boardCommentRepository.findByBoardEntity(boardEntity);

        for (BoardCommentEntity boardCommentEntity: boardCommentEntityList) {
            boardCommentDtoList.add(convertEntityToBoardCommentDto(boardCommentEntity, boardCommentEntity.getUserEntity()));
        }

        return boardCommentDtoList;
    }

    @Transactional
    public Long saveBoardComment(BoardCommentDto boardCommentDto) {
        return boardCommentRepository.save(boardCommentDto.toEntity()).getId();
    }


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
}
