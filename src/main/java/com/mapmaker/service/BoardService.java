package com.mapmaker.service;

import com.mapmaker.domain.entity.BoardEntity;
import com.mapmaker.domain.repository.BoardRepository;
import com.mapmaker.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public List<BoardDto> getBoardlist() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDTOList = new ArrayList<>();

        for ( BoardEntity boardEntity : boardEntities) {
            BoardDto boardDTO = BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .author(boardEntity.getAuthor())
                    .modifiedDate(boardEntity.getModifiedDate())
                    .build();

            boardDTOList.add(boardDTO);
        }

        return boardDTOList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .author(boardEntity.getAuthor())
                .modifiedDate(boardEntity.getModifiedDate())
                .build();

        return boardDTO;
    }

    @Transactional
    public Long savePost(BoardEntity boardEntity) {
        return boardRepository.save(boardEntity).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}

