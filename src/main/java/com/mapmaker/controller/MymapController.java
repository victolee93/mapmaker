package com.mapmaker.controller;

import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.dto.MapmakingDto;
import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.TravelDto;
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
    TravelService travelService;
    MarkerService markerService;
    UserService userService;
    private S3Service s3Service;

    @GetMapping("/mymap")
    public String dispMapList(Model model, Authentication authentication) {
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        // 여행정보 리스트
        List<TravelDto> travelDtoList = travelService.getTravelListByUser(userEntity);

        // 좌표 리스트 얻기
        List<MarkerDto> markerList = markerService.getMarkerList(travelDtoList);
        List<String> positionsList = markerService.getPositions(markerList);

        model.addAttribute("travelList", travelDtoList);
        model.addAttribute("positionsList", positionsList);

        return "/mymap/list";
    }

    @RequestMapping(value="/mymap/travel/{no}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTravelInfoJson(@PathVariable("no") Long no) {
        return travelService.getTravelInfo(no);
    }

    @RequestMapping(value="/mymap/travel/marker/{no}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTravelInfoJsonByMarker(@PathVariable("no") Long no) {
        return travelService.getTravelInfoByMarker(no);
    }

    @GetMapping("/mymap/making")
    public String dispMapMaking() {
        return "/mymap/making";
    }

    @PostMapping("/mymap/making")
    public String execMapMaking(MapmakingDto mapmakingDto, Authentication authentication, MultipartFile file) throws IOException {
        mapmakingDto.setTravelDto(mapmakingDto);
        mapmakingDto.setMarkerDto(mapmakingDto);

        UserEntity userEntity = userService.getUserByEmail(authentication.getName());

        String imgPath = s3Service.upload(file);
        TravelDto travelDto = mapmakingDto.getTravelDto();
        travelDto.setFilePath(imgPath);
        travelDto.setUserEntity(userEntity);
        TravelEntity travelEntity = travelDto.toEntity();

        // travel, marker INSERT
        MarkerDto markerDto = mapmakingDto.getMarkerDto();
        markerDto.setTravelEntity(travelEntity);
        markerService.saveMaker(markerDto);

        return "redirect:/mymap";
    }

}
