package com.mapmaker.controller;

import com.mapmaker.dto.MarkerDto;
import com.mapmaker.dto.TravelDto;
import com.mapmaker.service.MarkerService;
import com.mapmaker.service.TravelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class SearchController {
    private TravelService travelService;
    private MarkerService markerService;

    @GetMapping("/search/map/{no}")
    public String dispSearchMap(@PathVariable("no") Long no, Model model) {
        TravelDto travelDto = travelService.getTravelInfo(no);

        List<MarkerDto> markerList = markerService.getMarkerList(travelDto);
        List<String> positionsList = markerService.getPositions(markerList);

        model.addAttribute("travelInfo", travelDto);
        model.addAttribute("positionsList", positionsList);

        return "/map/detail";
    }

}
