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
    public String showEvent(ModelMap modelMap) throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get("C:\\Users\\gszaw\\OneDrive\\Pulpit\\prediction of winnings\\src\\main\\resources\\json\\data.json"));
        String jsonString = new String(jsonData);

        ObjectMapper mapper = new ObjectMapper();
        Events events = mapper.readValue(jsonString, Events.class);
//        List<Event> eventList = new ArrayList<>();
//        eventList.add(event);
        for (Event event : events.getEvents()){
            System.out.println(event.getSportEventId());
        }
//        modelMap.addAttribute(eventList);
        return "info.html";
    }
}
