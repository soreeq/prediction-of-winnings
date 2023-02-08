package winprediction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import winprediction.model.Competitor;
import winprediction.model.Event;
import winprediction.model.Events;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class EventController {
    @GetMapping("/event")
    public List<Event> showEvent(ModelMap modelMap, @RequestParam(value = "limit", defaultValue = "10") int numberOfMatchesToDisplay) throws Exception {
        Class<Events> eventsClass = Events.class;
        Events events = new Events();
        Events mappedEvents = events.deserializeJson("src\\main\\resources\\json\\data.json", eventsClass);

        if (numberOfMatchesToDisplay > mappedEvents.getEvents().size())
            numberOfMatchesToDisplay = mappedEvents.getEvents().size();

        List<Event> eventList = mappedEvents.getEvents().stream()
                .limit(Math.max(numberOfMatchesToDisplay, mappedEvents.getEvents().size()))
                .map(event -> {
                    String dateString = event.getStartDate();
                    event.setStartDate(dateString.replace("T", " ").replace(":00+00:00", " "));

                    String homeTeam = event.getCompetitors().get(0).getName();
                    String awayTeam = event.getCompetitors().get(1).getName();

                    double homeTeamProb = event.getProbabilityHomeTeamWinner();
                    double awayTeamProb = event.getProbabilityAwayTeamWinner();

                    if (homeTeamProb > awayTeamProb) {
                        event.setWillProbablyWin(homeTeam);
                        event.setHighestProbability(homeTeamProb);
                    } else if (homeTeamProb == awayTeamProb) {
                        event.setWillProbablyWin("draw");
                        event.setHighestProbability(homeTeamProb);
                    } else {
                        event.setWillProbablyWin(awayTeam);
                        event.setHighestProbability(awayTeamProb);
                    }
                    return event;
                }).collect(Collectors.toList());

        modelMap.addAttribute("numberOfMatchesToDisplay", numberOfMatchesToDisplay);
        modelMap.addAttribute("events", eventList);

        return eventList;
    }

}
