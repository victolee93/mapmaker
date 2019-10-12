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
        return "/user/login";
    }

    // 회원상세 페이지
    @GetMapping("/user/info")
    public String dispInfo() {
        return "/user/info";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "/user/signup";
    }

    // 회원가입
    @PostMapping("/user/signup")
    public String execSignup(UserDto userDto) {
        userService.joinUser(userDto);

        return "redirect:/user/login";
    }

    // 비밀번호 찾기
    @GetMapping("/user/find")
    public String dispFind() {
        return "/user/find";
    }
}
