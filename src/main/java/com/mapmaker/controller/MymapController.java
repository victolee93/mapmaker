package com.mapmaker.controller;

import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.MapmakingDto;
import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.Travel.TravelDto;
import com.mapmaker.service.MarkerService;
import com.mapmaker.service.TravelService;
import com.mapmaker.service.UserService;
import com.mapmaker.service.aws.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@AllArgsConstructor
public class MymapController {
    private TravelService travelService;
    private MarkerService markerService;
    private UserService userService;
    private S3Service s3Service;

    // mymap 메인
    @GetMapping("/mymap")
    public String dispMapList(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        // 여행정보 리스트
        List<TravelDto> travelDtoList = travelService.getListByUser(userEntity);

        // 위치정보 리스트
        List<MarkerDto> markerList = markerService.getList(travelDtoList);
        List<String> positionsList = markerService.getPositions(markerList);

        model.addAttribute("travelList", travelDtoList);
        model.addAttribute("positionsList", positionsList);

        return "/mymap/list";
    }

    // 여행 상세
    @RequestMapping(value="/mymap/travel/{no}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTravelInfoJson(@PathVariable("no") Long no) {
        return travelService.getDetailJson(no);
    }

    // 마커 정보를 통해, 여행 상세 정보를 응답
    @RequestMapping(value="/mymap/travel/marker/{no}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTravelInfoJsonByMarker(@PathVariable("no") Long no) {
        return travelService.getDetailJsonByMarker(no);
    }

    // 여행정보 등록 페이지
    @GetMapping("/mymap/making")
    public String dispMapMaking() {
        return "/mymap/making";
    }

    // 여행정보 등록
    @PostMapping("/mymap/making")
    public String execMapMaking(MapmakingDto mapmakingDto, Authentication authentication, MultipartFile file) throws IOException {
        mapmakingDto.setTravelDto(mapmakingDto);
        mapmakingDto.setMarkerDto(mapmakingDto);

        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        // travel dto set
        String imgPath = s3Service.upload(file);
        TravelDto travelDto = mapmakingDto.getTravelDto();
        travelDto.setFilePath(imgPath);
        travelDto.setUserEntity(userEntity);

        // marker dto set
        MarkerDto markerDto = mapmakingDto.getMarkerDto();
        markerDto.setTravelEntity(travelDto.toEntity());

        // travel, marker INSERT
        markerService.saveMaker(markerDto);

        return "redirect:/mymap";
    }

}
