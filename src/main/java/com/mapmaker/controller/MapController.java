package com.mapmaker.controller;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.*;
import com.mapmaker.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class MapController {
    private TravelService travelService;
    private MarkerService markerService;
    private UserService userService;
    private LikeService likeService;
    private CommentService commentService;

    @GetMapping("/map")
    public String list(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        List<TravelDto> recentTravelList = travelService.getRecentTravelList(userEntity);
        List<TravelDto> popularTravelList = travelService.getPopularTravelList(userEntity);

        model.addAttribute("recentTravelList", recentTravelList);
        model.addAttribute("popularTravelList", popularTravelList);

        return "/map/list";
    }

    @GetMapping("/map/{no}")
    public String dispSearchMap(@PathVariable("no") Long no, Model model) {
        TravelDto travelDto = travelService.getTravelInfo(no);

        List<MarkerDto> markerList = markerService.getMarkerList(travelDto);
        List<String> positionsList = markerService.getPositions(markerList);

        List<TravelCommentDto> travelComments = commentService.getTravelCommentList(travelDto.toEntity());

        model.addAttribute("travelInfo", travelDto);
        model.addAttribute("positionsList", positionsList);
        model.addAttribute("replies", travelComments);

        return "/map/detail";
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

    @PostMapping("/map/{no}/comment")
    public String execComment(@PathVariable("no") Long travelId, Authentication authentication, TravelCommentDto travelCommentDto) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        TravelEntity travelEntity = travelService.getTravelInfo(travelId).toEntity();

        travelCommentDto.setUserEntity(userEntity);
        travelCommentDto.setTravelEntity(travelEntity);

        commentService.saveTravelComment(travelCommentDto);

        return "redirect:/map/" + travelId;
    }
}
