package winprediction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import winprediction.model.Competitor;
import winprediction.model.Event;
import winprediction.model.Events;
import winprediction.service.EventService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/event")
    public List<Event> showEvent(ModelMap modelMap, @RequestParam(value = "limit", defaultValue = "10") int numberOfMatchesToDisplay) throws Exception {

        List<Event> eventList;
        eventList = eventService.displayEvents(numberOfMatchesToDisplay);

        modelMap.addAttribute("numberOfMatchesToDisplay", eventList.size());
        modelMap.addAttribute("events", eventList);

        return eventList;
    }
}
