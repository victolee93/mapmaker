package com.mapmaker.controller;

import com.mapmaker.dto.UserDto;
import com.mapmaker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
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
    @GetMapping("/user/signup")
    public String dispSignup(UserDto userDto) {
        return "/user/signup";
    }

    // 회원가입
    @PostMapping("/user/signup")
    public String execSignup(@Valid UserDto userDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 입력 데이터 유지
            model.addAttribute("userDto", userDto);

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()){
                // validation 통과 못한 필드와 메시지
                model.addAttribute(key, validatorResult.get(key));
            }

            return "/user/signup";
        }

        userService.joinUser(userDto);
        return "redirect:/user/login";
    }

    // 비밀번호 찾기
    @GetMapping("/user/find")
    public String dispFind() {
        return "/user/find";
    }
}
