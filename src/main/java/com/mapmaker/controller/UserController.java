package com.mapmaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/login")
    public String login() {
        return "/user/login.html";
    }

    @GetMapping("/user/info")
    public String info() {
        return "/user/info.html";
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "/user/signup.html";
    }

    @GetMapping("/user/find")
    public String find() {
        return "/user/find.html";
    }
}
