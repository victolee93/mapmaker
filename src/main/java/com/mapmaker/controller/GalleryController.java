package com.mapmaker.controller;

import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.GalleryDto;
import com.mapmaker.service.GalleryService;
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
import java.util.List;

@Controller
@AllArgsConstructor
public class GalleryController {
    private GalleryService galleryService;
    private UserService userService;
    private S3Service s3Service;

    @GetMapping("/gallery")
    public String dispGalleryList(Model model) {
        List<GalleryDto> galleryList = galleryService.getRecentList();

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

    @RequestMapping(value="/gallery/{no}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String dispGalleryDetail(@PathVariable("no") Long no) {
        return galleryService.getGalleryInfo(no);
    }
}