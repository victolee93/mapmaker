package com.mapmaker.controller;

import com.mapmaker.dto.TravelDto;
import com.mapmaker.service.TravelService;
import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MapController {
    private TravelService travelService;

    @GetMapping("/map")
    public String list(Model model) {
        List<TravelDto> recentTravelList = travelService.getRecentTravelList();

        model.addAttribute("recentTravelList", recentTravelList);

        return "/map/list";
    }

}
