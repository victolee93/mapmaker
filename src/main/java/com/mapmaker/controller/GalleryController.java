package com.mapmaker.controller;

import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.GalleryDto;
import com.mapmaker.service.GalleryService;
import com.mapmaker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
// TODO - 파일 업로드 : Object Storage
public class GalleryController {
    private GalleryService galleryService;
    private UserService userService;

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
        galleryService.savePost(galleryDto);

        return "redirect:/gallery";
    }
}