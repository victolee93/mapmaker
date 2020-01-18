package com.mapmaker.controller;

import com.mapmaker.domain.entity.Gallery.GalleryEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.Gallery.GalleryCommentDto;
import com.mapmaker.dto.Gallery.GalleryDto;
import com.mapmaker.dto.Gallery.GalleryLikeDto;
import com.mapmaker.service.CommentService;
import com.mapmaker.service.GalleryService;
import com.mapmaker.service.LikeService;
import com.mapmaker.service.UserService;
import com.mapmaker.service.aws.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class GalleryController {
    private GalleryService galleryService;
    private UserService userService;
    private S3Service s3Service;
    private LikeService likeService;
    private CommentService commentService;

    // 갤러리 메인
    @GetMapping("/gallery")
    public String dispGalleryList(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        List<GalleryDto> galleryList = galleryService.getRecentList();

        for (GalleryDto galleryDto: galleryList) {
            // 현재 유저가 좋아요를 체크했는지 여부
            Boolean checked = likeService.isUserCheckedGalleryLike(userEntity, galleryDto);
            galleryDto.setLikeChecked(checked);
        }

        model.addAttribute("galleryList", galleryList);

        return "/gallery/list";
    }

    // 갤러리 글 등록
    @PostMapping("/gallery")
    public String execGalleryWrite(GalleryDto galleryDto, Authentication authentication, MultipartFile file) throws IOException {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        galleryDto.setAuthor(userEntity.getNickname()); // 작성자

        String imgPath = s3Service.upload(file);
        galleryDto.setFilePath(imgPath);    // 파일 경로

        galleryService.savePost(galleryDto);

        return "redirect:/gallery";
    }

    // 갤러리 상세
    @RequestMapping(value="/gallery/{no}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String dispGalleryDetail(@PathVariable("no") Long no) {
        return galleryService.getDetailAndCommentsJson(no);
    }

    // 갤러리 좋아요
    @RequestMapping(value="/gallery/{no}/like", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String execGalleryLike(@PathVariable("no") Long galleryId, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        GalleryEntity galleryEntity = galleryService.getDetail(galleryId);

        GalleryLikeDto galleryLikeDto = new GalleryLikeDto();
        galleryLikeDto.setUserEntity(userEntity);
        galleryLikeDto.setGalleryEntity(galleryEntity);

        likeService.saveGalleryLike(galleryLikeDto);
        return "null";
    }

    // 갤러리 댓글 등록
    @RequestMapping(value="/gallery/{no}/comment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> execGalleryCommentWrite(@PathVariable("no") Long galleryId,
                                           Authentication authentication,
                                           GalleryCommentDto galleryCommentDto) {

        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        GalleryEntity galleryEntity = galleryService.getDetail(galleryId);

        galleryCommentDto.setUserEntity(userEntity);
        galleryCommentDto.setGalleryEntity(galleryEntity);

        commentService.saveGalleryComment(galleryCommentDto);

        // ajax 요청이므로 등록된 댓글 정보를 Json으로 반환
        Map<String, String> returnJson = new HashMap<>();
        returnJson.put("username", userEntity.getNickname());
        returnJson.put("content", galleryCommentDto.getContent());
        returnJson.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return returnJson;
    }
}