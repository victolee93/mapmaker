package com.mapmaker.controller;

import com.mapmaker.domain.entity.Travel.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.*;
import com.mapmaker.dto.Travel.TravelCommentDto;
import com.mapmaker.dto.Travel.TravelDto;
import com.mapmaker.dto.Travel.TravelLikeDto;
import com.mapmaker.service.*;
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
    private MarkerService markerService;
    private UserService userService;
    private LikeService likeService;
    private CommentService commentService;

    // map 메인
    @GetMapping("/map")
    public String dispTravelList(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        // 최근 여행 정보
        List<TravelDto> recentTravelList = travelService.getRecentList(userEntity);

        // 인기 여행 정보
        List<TravelDto> popularTravelList = travelService.getPopularList(userEntity);

        model.addAttribute("recentTravelList", recentTravelList);
        model.addAttribute("popularTravelList", popularTravelList);

        return "/map/list";
    }

    // 지도 상세정보
    @GetMapping("/map/{no}")
    public String dispTravelDetail(@PathVariable("no") Long no, Model model) {
        TravelDto travelDto = travelService.getDetail(no);

        // 마커 리스트
        List<MarkerDto> markerList = markerService.getList(travelDto);
        // 위치정보 리스트
        List<String> positionsList = markerService.getPositions(markerList);
        // 게시글 리스트
        List<TravelCommentDto> travelCommentList = commentService.getTravelCommentList(travelDto);

        model.addAttribute("travelInfo", travelDto);
        model.addAttribute("positionsList", positionsList);
        model.addAttribute("replies", travelCommentList);

        return "/map/detail";
    }

    // 여행 좋아요
    @RequestMapping(value="/map/{no}/like", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String execMapLike(@PathVariable("no") Long travelId, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        TravelEntity travelEntity = travelService.getDetail(travelId).toEntity();

        TravelLikeDto travelLikeDto = new TravelLikeDto();
        travelLikeDto.setUserEntity(userEntity);
        travelLikeDto.setTravelEntity(travelEntity);

        likeService.saveTravelLike(travelLikeDto);
        return "null";
    }

    // 여행 댓글
    @PostMapping("/map/{no}/comment")
    public String execMapComment(@PathVariable("no") Long travelId, Authentication authentication, TravelCommentDto travelCommentDto) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        TravelEntity travelEntity = travelService.getDetail(travelId).toEntity();

        travelCommentDto.setUserEntity(userEntity);
        travelCommentDto.setTravelEntity(travelEntity);

        commentService.saveTravelComment(travelCommentDto);

        return "redirect:/map/" + travelId;
    }
}
