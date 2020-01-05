package com.mapmaker.controller;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.TravelDto;
import com.mapmaker.dto.TravelLikeDto;
import com.mapmaker.service.LikeService;
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
public class MapController {
    private TravelService travelService;
    private UserService userService;
    private LikeService likeService;

    @GetMapping("/map")
    public String list(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        List<TravelDto> recentTravelList = travelService.getRecentTravelList();

        for(TravelDto travelDto: recentTravelList) {
            Boolean checked = likeService.isUserCheckedTravelLike(userEntity, travelDto.toEntity());
            travelDto.setChecked(checked);
        }

        model.addAttribute("recentTravelList", recentTravelList);

        return "/map/list";
    }

    @RequestMapping(value="/map/{no}/like", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String execLike(@PathVariable("no") Long travelId, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        TravelEntity travelEntity = travelService.getTravelInfo(travelId).toEntity();

        TravelLikeDto travelLikeDto = new TravelLikeDto();
        travelLikeDto.setUserEntity(userEntity);
        travelLikeDto.setTravelEntity(travelEntity);

        likeService.saveTravelLike(travelLikeDto);
        return "null";
    }

}
