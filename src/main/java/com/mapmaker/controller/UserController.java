package com.mapmaker.controller;

import com.mapmaker.dto.UserDto;
import com.mapmaker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

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
    // 입력 데이터 유지를 위해 매개변수에 UserDto를 명시해줘야 함
    @GetMapping("/user/signup")
    public String dispSignup(UserDto userDto) {
        return "/user/signup";
    }

    // 회원가입
    @PostMapping("/user/signup")
    public String execSignup(@Valid UserDto userDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("userDto", userDto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "/user/signup";
        }

        userService.signUp(userDto);
        return "redirect:/user/login";
    }

    // 비밀번호 찾기
    @GetMapping("/user/find")
    public String dispFind() {
        return "/user/find";
    }
}
