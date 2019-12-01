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
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/mymap/{no}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTravelInfoJson(@PathVariable("no") Long no) {
        return travelService.getTravelInfo(no);
    }

    @GetMapping("/mymap/making")
    public String dispMapMaking() {
        return "/mymap/making";
    }

    @PostMapping("/mymap/making")
    public String execMapMaking(MapmakingDto mapmakingDto, Authentication authentication) {
        mapmakingDto.setTravelDto(mapmakingDto);
        mapmakingDto.setMarkerDto(mapmakingDto);

        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        TravelDto travelDto = mapmakingDto.getTravelDto();
        travelDto.setUserEntity(userEntity);
        TravelEntity travelEntity = travelDto.toEntity();

        // travel, marker INSERT
        MarkerDto markerDto = mapmakingDto.getMarkerDto();
        markerDto.setTravelEntity(travelEntity);
        markerService.saveMaker(markerDto);

        return "redirect:/mymap";
    }

}
