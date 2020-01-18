package com.mapmaker.controller;

import com.mapmaker.domain.entity.Board.BoardEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.Board.BoardCommentDto;
import com.mapmaker.dto.Board.BoardDto;
import com.mapmaker.dto.Board.BoardLikeDto;
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

    // 게시판 메인 페이지
    @GetMapping("/board")
    public String dispBoardList(Model model) {
        List<BoardDto> boardList = boardService.getBoardlist();

        model.addAttribute("boardList", boardList);
        return "/board/list";
    }

    // 게시글 등록
    @PostMapping("/board")
    public String execPostWrite(BoardDto boardDto, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        boardService.savePost(boardDto, userEntity);

        return "redirect:/board";
    }

    // 게시글 상세
    @GetMapping("/board/{no}")
    public String dispPostDetail(@PathVariable("no") Long no, Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        BoardDto boardDTO = boardService.getPost(no);

        // 댓글 목록
        List<BoardCommentDto> boardComments = commentService.getGalleryCommentList(boardDTO);

        // 현재 유저가 좋아요를 체크했는지 여부
        Boolean likeChecked = likeService.isUserCheckedBoardLike(userEntity, boardDTO);

        model.addAttribute("board", boardDTO);
        model.addAttribute("replies", boardComments);
        model.addAttribute("likeChecked", likeChecked);

        return "/board/detail";
    }

    // 게시글 수정
    @PutMapping("/board/{no}")
    public String execPostUpdate(BoardDto boardDto, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        boardService.savePost(boardDto, userEntity);

        return "redirect:/board";
    }

    // 게시글 삭제
    @DeleteMapping("/board/{no}")
    public String execPostDelete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board";
    }

    // 게시글 좋아요
    @RequestMapping(value="/board/{no}/like", method = RequestMethod.GET)
    @ResponseBody
    public String execPostLike(@PathVariable("no") Long boardId, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        BoardEntity boardEntity = boardService.getPost(boardId).toEntity();

        BoardLikeDto boardLikeDto = new BoardLikeDto();
        boardLikeDto.setUserEntity(userEntity);
        boardLikeDto.setBoardEntity(boardEntity);

        likeService.saveBoardLike(boardLikeDto);

        return "null";
    }

    // 게시글 댓글 등록
    @PostMapping("/board/{no}/comment")
    public String execPostCommentWrite(@PathVariable("no") Long boardId,
                                       Authentication authentication,
                                       BoardCommentDto boardCommentDto) {

        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        BoardEntity boardEntity = boardService.getPost(boardId).toEntity();

        boardCommentDto.setUserEntity(userEntity);
        boardCommentDto.setBoardEntity(boardEntity);

        commentService.saveBoardComment(boardCommentDto);

        return "redirect:/board/" + boardId;
    }
}