package com.mapmaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MymapController {

    @GetMapping("/mymap")
    public String list() {
        return "/mymap/list";
    }

    @GetMapping("/mymap/making")
    public String makingView() {
        return "/mymap/making";
    }

}
