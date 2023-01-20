package winprediction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {
    @GetMapping("/event")
    public String showEvent(ModelMap modelMap) throws Exception {

        byte[] jsonData = Files.readAllBytes(Paths.get("C:\\Users\\gszaw\\OneDrive\\Pulpit\\prediction of winnings\\src\\main\\resources\\json\\data.json"));
        String jsonString = new String(jsonData);

        ObjectMapper mapper = new ObjectMapper();
        Events events = mapper.readValue(jsonString, Events.class);
        List<Event> eventList = new ArrayList<>();

        int counter = 0;
        for (Event event : events.getEvents()){
            eventList.add(event);
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

            counter++;
            if(counter == 10)
                break;
        }

        modelMap.addAttribute("events", eventList);

        return "info";
    }
}
