package winprediction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import winprediction.model.Competitor;
import winprediction.model.Event;
import winprediction.model.Events;
import winprediction.service.EventService;
import winprediction.service.SortService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SortController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SortService sortService;

    @GetMapping("/sort")
    public List<String> sortCompetitors(ModelMap modelMap, @RequestParam(value = "limit", defaultValue = "100") int numberOfMatchesToDisplay) throws Exception {
        List<String> sortedCompetitorList = sortService.sortCompetitors(numberOfMatchesToDisplay);

        modelMap.addAttribute("sortedTeamList", sortedCompetitorList);
        modelMap.addAttribute("teamListLength", sortedCompetitorList.size());

        return sortedCompetitorList;
    }

}
