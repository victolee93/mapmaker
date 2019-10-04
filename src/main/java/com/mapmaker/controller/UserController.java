package com.mapmaker.controller;

import com.mapmaker.dto.UserDto;
import com.mapmaker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/user/login.html";
    }

    // 로그인
    @PostMapping("/user/login")
    public String execLogin(UserDto userDto) {
        System.out.println("로그인 처리됨");
        return "redirect:/map";
    }

    // 로그아웃
    @PostMapping("/user/logout")
    public String execLogout(UserDto userDto) {
        return "redirect:/map";
    }

    // 회원상세 페이지
    @GetMapping("/user/info")
    public String dispInfo() {
        return "/user/info.html";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "/user/signup.html";
    }

    // 회원가입
    @PostMapping("/user/signup")
    public String execSignup(UserDto userDto) {
        userService.joinUser(userDto);

        return "redirect:/map";
    }

    // 비밀번호 찾기
    @GetMapping("/user/find")
    public String dispFind() {
        return "/user/find.html";
    }
}
