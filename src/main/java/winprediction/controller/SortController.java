package winprediction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import winprediction.model.Competitor;
import winprediction.model.Event;
import winprediction.model.Events;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SortController {

    @GetMapping("/sort")
    public List<String> sortCompetitors(ModelMap modelMap) throws IOException {
        Events events = new Events();
        Class<Events> eventsClass = Events.class;
        Events mappedEvents = events.deserializeJson("src\\main\\resources\\json\\data.json", eventsClass);

        List<String> sortedCompetitorList = mappedEvents.getEvents().stream()
                .flatMap(event -> event.getCompetitors().stream().map(Competitor::getName))
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        modelMap.addAttribute("sortedTeamList", sortedCompetitorList);
        modelMap.addAttribute("teamListLength", sortedCompetitorList.size());

        return sortedCompetitorList;
    }

}
