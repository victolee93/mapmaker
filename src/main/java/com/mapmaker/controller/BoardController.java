package com.mapmaker.controller;

import com.mapmaker.dto.BoardDTO;
import com.mapmaker.service.BoardService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    /*********************
     * board
     *********************/
    @GetMapping("/board")
    public String list(Model model) {
        List<BoardDTO> boardList = boardService.getBoardlist();

        model.addAttribute("boardList", boardList);
        return "/board/list.html";
    }

    @PostMapping("/board")
    public String write(BoardDTO boardDTO) {
        boardDTO.setAuthor("우르릉");   // TODO 세션으로 처리
        boardService.savePost(boardDTO.toEntity());

        return "redirect:/board";
    }

    /*********************
     * board/{no}
     *********************/
    @GetMapping("/board/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDTO boardDTO = boardService.getPost(no);

        model.addAttribute("boardDTO", boardDTO);
        return "/board/detail.html";
    }

    @PutMapping("/board/{no}")
    public String update(BoardDTO boardDTO) {
        boardDTO.setAuthor("우르릉");   // TODO 세션으로 처리
        boardService.savePost(boardDTO.toEntity());

        return "redirect:/board";
    }

    @DeleteMapping("/board/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board";
    }

}