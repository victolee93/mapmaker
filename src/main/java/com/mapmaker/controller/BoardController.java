package com.mapmaker.controller;

import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.BoardDto;
import com.mapmaker.service.BoardService;
import com.mapmaker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;
    private UserService userService;

    // 게시판 리스트 페이지
    @GetMapping("/board")
    public String dispList(Model model) {
        List<BoardDto> boardList = boardService.getBoardlist();

        model.addAttribute("boardList", boardList);
        return "/board/list";
    }

    // 게시글 등록
    @PostMapping("/board")
    public String execWrite(BoardDto boardDTO, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        boardDTO.setAuthor(userEntity.getNickname());
        boardService.savePost(boardDTO.toEntity());

        return "redirect:/board";
    }

    // 게시판 상세 페이지
    @GetMapping("/board/{no}")
    public String dispDetail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDTO", boardDTO);
        return "/board/detail";
    }

    // 게시판 수정
    @PutMapping("/board/{no}")
    public String execUpdate(BoardDto boardDTO, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        boardDTO.setAuthor(userEntity.getNickname());
        boardService.savePost(boardDTO.toEntity());

        return "redirect:/board";
    }

    // 게시판 삭제
    @DeleteMapping("/board/{no}")
    public String execDelete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board";
    }

}