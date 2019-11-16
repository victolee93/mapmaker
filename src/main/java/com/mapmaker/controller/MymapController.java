package com.mapmaker.controller;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.MapmakingDto;
import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.TravelDto;
import com.mapmaker.service.MarkerService;
import com.mapmaker.service.TravelService;
import com.mapmaker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class MymapController {
    TravelService travelService;
    MarkerService markerService;
    UserService userService;

    @GetMapping("/mymap")
    public String dispMapList(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        TravelDto travelDto = travelService.getTravelByUser(userEntity);

        // TODO 마커를 클릭하면 정보를 가져올 수 있도록 처리 ( 유저 + 위치정보 )
        model.addAttribute("travelDto", travelDto);

        return "/mymap/list";
    }

    @GetMapping("/mymap/making")
    public String dispMapMaking() {
        return "/mymap/making";
    }

    @PostMapping("/mymap/making")
    public String execMapMaking(MapmakingDto mapmakingDto, Authentication authentication) {
        mapmakingDto.setTravelDto(mapmakingDto);
        mapmakingDto.setMarkerDto(mapmakingDto);

        // travel INSERT
        TravelDto travelDto = mapmakingDto.getTravelDto();
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        travelDto.setUserEntity(userEntity);
        travelService.saveMap(travelDto);

        // marker INSERT
        TravelEntity travelEntity = travelDto.toEntity();
        MarkerDto markerDto = mapmakingDto.getMarkerDto();
        markerDto.setTravelEntity(travelEntity);
        markerService.saveMaker(markerDto);

        return "redirect:/mymap";
    }

}
