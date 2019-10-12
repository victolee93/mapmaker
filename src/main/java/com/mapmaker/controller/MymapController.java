package com.mapmaker.controller;

import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.TravelDto;
import com.mapmaker.service.TravelService;
import com.mapmaker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
public class MymapController {
    TravelService travelService;
    UserService userService;

    @GetMapping("/mymap")
    public String list() {
        return "/mymap/list";
    }

    @GetMapping("/mymap/making")
    public String dispMapMaking() {
        return "/mymap/making";
    }

    @PostMapping("/mymap/making")
    public String execMapMaking(TravelDto travelDto, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        travelDto.setUserEntity(userEntity);

        travelService.saveMap(travelDto);

        // TODO 여행정보뿐만 아니라 지도 location도 같이 저장하는 로직 구현

        return "redirect:/mymap";
    }

}
