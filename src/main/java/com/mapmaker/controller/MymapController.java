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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@AllArgsConstructor
public class MymapController {
    TravelService travelService;
    MarkerService markerService;
    UserService userService;

    @GetMapping("/mymap")
    public String dispMapList(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        List<MarkerDto> markerList = markerService.getMarkerList(userEntity);

        List<String> positionsList = markerService.getPositions(markerList);
        model.addAttribute("positionsList", positionsList);

        return "/mymap/list";
    }

    // TODO 여행정보는 검색용으로만 제공
//    @GetMapping("/mymap/search")
//    public String dispMapList(Model model, Authentication authentication) {
//        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
//        List<TravelDto> travelDtoList = travelService.getTravelListByUser(userEntity);
//
//        model.addAttribute("travelDtoList", travelDtoList);
//
//        return "/mymap/list";
//    }

//    @GetMapping("/mymap/{no}")
//    public String dispMapInfo(@PathVariable("no") Long no, Model model, Authentication authentication) {
//        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
//        TravelDto travelDto = travelService.getTravelByUser(userEntity);
//
//        // TODO 마커를 클릭하면 정보를 가져올 수 있도록 처리 ( ajax, Modal로 구현 )
//        model.addAttribute("travelDto", travelDto);
//
//        return "/mymap/list";
//    }


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

        // marker INSERT
        TravelEntity travelEntity = travelDto.toEntity();
        MarkerDto markerDto = mapmakingDto.getMarkerDto();
        markerDto.setTravelEntity(travelEntity);
        markerService.saveMaker(markerDto);

        return "redirect:/mymap";
    }

}
