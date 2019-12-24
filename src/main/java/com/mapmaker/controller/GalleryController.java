package com.mapmaker.controller;

import com.mapmaker.domain.entity.GalleryEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.GalleryCommentDto;
import com.mapmaker.dto.GalleryDto;
import com.mapmaker.dto.GalleryLikeDto;
import com.mapmaker.service.CommentService;
import com.mapmaker.service.GalleryService;
import com.mapmaker.service.LikeService;
import com.mapmaker.service.UserService;
import com.mapmaker.service.aws.S3Service;
import com.mapmaker.util.JsonManager;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/gallery")
    public String dispGalleryList(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        List<GalleryDto> galleryList = galleryService.getRecentList();

        for(GalleryDto galleryDto: galleryList) {
            GalleryEntity galleryEntity = galleryDto.toEntity();
            Long totalLike = likeService.getCountGalleryLike(galleryEntity);
            Boolean checked = likeService.isUserCheckedGalleryLike(userEntity, galleryEntity);

            galleryDto.setTotalLike(totalLike);
            galleryDto.setChecked(checked);

        }

        model.addAttribute("galleryList", galleryList);

        return "/gallery/list";
    }

    @PostMapping("/gallery")
    public String execWrite(GalleryDto galleryDto, Authentication authentication, MultipartFile file) throws IOException {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        galleryDto.setAuthor(userEntity.getNickname());

        String imgPath = s3Service.upload(file);
        galleryDto.setFilePath(imgPath);
        galleryService.savePost(galleryDto);

        return "redirect:/gallery";
    }

    @RequestMapping(value="/gallery/{no}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getGalleryDetail(@PathVariable("no") Long no) {
        return galleryService.getGalleryInfoJson(no);
    }

    @RequestMapping(value="/gallery/{no}/like", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String execLike(@PathVariable("no") Long galleryId, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        GalleryEntity galleryEntity = galleryService.getGalleryInfo(galleryId).toEntity();

        GalleryLikeDto galleryLikeDto = new GalleryLikeDto();
        galleryLikeDto.setUserEntity(userEntity);
        galleryLikeDto.setGalleryEntity(galleryEntity);

        likeService.saveGalleryLike(galleryLikeDto);
        return "null";
    }


    @RequestMapping(value="/gallery/{no}/comment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> execComment(@PathVariable("no") Long galleryId, Authentication authentication, GalleryCommentDto galleryCommentDto) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        GalleryEntity galleryEntity = galleryService.getGalleryInfo(galleryId).toEntity();

        galleryCommentDto.setUserEntity(userEntity);
        galleryCommentDto.setGalleryEntity(galleryEntity);

        commentService.saveGalleryComment(galleryCommentDto);

        Map<String, String> returnJson = new HashMap<>();
        returnJson.put("username", userEntity.getNickname());
        returnJson.put("content", galleryCommentDto.getContent());
        return returnJson;
    }
}