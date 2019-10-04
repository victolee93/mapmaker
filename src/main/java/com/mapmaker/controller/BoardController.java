package com.mapmaker.controller;

import com.mapmaker.dto.BoardDto;
import com.mapmaker.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    // 게시판 리스트 페이지
    @GetMapping("/board")
    public String dispList(Model model) {
        List<BoardDto> boardList = boardService.getBoardlist();

        model.addAttribute("boardList", boardList);
        return "/board/list.html";
    }

    // 게시글 등록
    @PostMapping("/board")
    public String execWrite(BoardDto boardDTO) {
        boardDTO.setAuthor("우르릉");   // TODO 세션으로 처리
        boardService.savePost(boardDTO.toEntity());

        return "redirect:/board";
    }

    // 게시판 상세 페이지
    @GetMapping("/board/{no}")
    public String dispDetail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDTO", boardDTO);
        return "/board/detail.html";
    }

    // 게시판 수정
    @PutMapping("/board/{no}")
    public String execUpdate(BoardDto boardDTO) {
        boardDTO.setAuthor("우르릉");   // TODO 세션으로 처리
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