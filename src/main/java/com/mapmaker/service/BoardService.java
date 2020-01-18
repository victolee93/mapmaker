package com.mapmaker.service;

import com.mapmaker.domain.entity.Board.BoardEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.Board.BoardRepository;
import com.mapmaker.dto.Board.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    // 게시글 리스트
    @Transactional
    public List<BoardDto> getBoardlist() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDTOList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            BoardDto boardDto = convertEntityToDto(boardEntity);

            // 좋아요, 댓글 개수
            boardDto.setLikeCount(boardEntity.getBoardLikes().size());
            boardDto.setCommentCount(boardEntity.getBoardComments().size());

            boardDTOList.add(boardDto);
        }

        return boardDTOList;
    }

    // 게시글 상세
    @Transactional
    public BoardDto getPost(Long no) {
        BoardEntity boardEntity = boardRepository.findById(no).get();
        return convertEntityToDto(boardEntity);
    }

    // 게시글 등록/수정
    @Transactional
    public void savePost(BoardDto boardDto, UserEntity userEntity) {
        boardDto.setAuthor(userEntity.getNickname());
        boardRepository.save(boardDto.toEntity());
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .author(boardEntity.getAuthor())
                .modifiedDate(boardEntity.getModifiedDate())
                .build();
    }
}

