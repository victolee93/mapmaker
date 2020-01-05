package com.mapmaker.controller;

import com.mapmaker.domain.entity.BoardEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.BoardCommentDto;
import com.mapmaker.dto.BoardDto;
import com.mapmaker.dto.BoardLikeDto;
import com.mapmaker.service.BoardService;
import com.mapmaker.service.CommentService;
import com.mapmaker.service.LikeService;
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
    private LikeService likeService;
    private CommentService commentService;

    // 게시판 리스트 페이지
    @GetMapping("/board")
    public String dispList(Model model) {
        List<BoardDto> boardList = boardService.getBoardlist();

        model.addAttribute("boardList", boardList);
        return "/board/list";
    }

    // 게시글 등록
    @PostMapping("/board")
    public String execWrite(BoardDto boardDto, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        boardDto.setAuthor(userEntity.getNickname());
        boardService.savePost(boardDto);

        return "redirect:/board";
    }

    // 게시판 상세 페이지
    @GetMapping("/board/{no}")
    public String dispDetail(@PathVariable("no") Long no, Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        BoardDto boardDTO = boardService.getPost(no);

        List<BoardCommentDto> boardComments = commentService.getGalleryCommentList(boardDTO.toEntity());

        Boolean likeChecked = likeService.isUserCheckedBoardLike(userEntity, boardDTO.toEntity());

        model.addAttribute("board", boardDTO);
        model.addAttribute("replies", boardComments);
        model.addAttribute("likeChecked", likeChecked);

        return "/board/detail";
    }

    // 게시판 수정
    @PutMapping("/board/{no}")
    public String execUpdate(BoardDto boardDto, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        boardDto.setAuthor(userEntity.getNickname());
        boardService.savePost(boardDto);

        return "redirect:/board";
    }

    // 게시판 삭제
    @DeleteMapping("/board/{no}")
    public String execDelete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board";
    }

    @RequestMapping(value="/board/{no}/like", method = RequestMethod.GET)
    @ResponseBody
    public String execLike(@PathVariable("no") Long boardId, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        BoardEntity boardEntity = boardService.getPost(boardId).toEntity();

        BoardLikeDto boardLikeDto = new BoardLikeDto();
        boardLikeDto.setUserEntity(userEntity);
        boardLikeDto.setBoardEntity(boardEntity);

        likeService.saveBoardLike(boardLikeDto);
        return "null";
    }

    @PostMapping("/board/{no}/comment")
    public String execComment(@PathVariable("no") Long boardId, Authentication authentication, BoardCommentDto boardCommentDto) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        BoardEntity boardEntity = boardService.getPost(boardId).toEntity();

        boardCommentDto.setUserEntity(userEntity);
        boardCommentDto.setBoardEntity(boardEntity);

        commentService.saveBoardComment(boardCommentDto);

        return "redirect:/board/" + boardId;
    }
}