package com.mapmaker.service;

import com.mapmaker.domain.entity.BoardEntity;
import com.mapmaker.domain.repository.BoardRepository;
import com.mapmaker.dto.BoardDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public List<BoardDTO> getBoardlist() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for ( BoardEntity boardEntity : boardEntities) {
            BoardDTO boardDTO = BoardDTO.builder()
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
    public BoardDTO getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDTO boardDTO = BoardDTO.builder()
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

