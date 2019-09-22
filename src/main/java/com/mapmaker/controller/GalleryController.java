package com.mapmaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {

    @GetMapping("/gallery")
    public String index() {
        return "/gallery/list.html";
    }
}