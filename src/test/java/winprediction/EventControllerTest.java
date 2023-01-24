package winprediction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class EventControllerTest {

    @InjectMocks
    EventController eventController;
    @Mock
    private ModelMap modelMap;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapDataFromJsonTest() throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get("C:\\Users\\gszaw\\OneDrive\\Pulpit\\prediction of winnings\\src\\main\\resources\\json\\data.json"));
        String jsonString = new String(jsonData);
        ObjectMapper mapper = new ObjectMapper();
        Events events = mapper.readValue(jsonString, Events.class);
        Set<Competitor> expected = new TreeSet<>(Comparator.comparing(Competitor::getName));

        for (Event event : events.getEvents()){
            expected.add(event.getCompetitors().get(0));
        }

        Set<Competitor> result = eventController.mapDataFromJson(modelMap);

        assertEquals(expected, result);
    }

    @Test
    public void showEventTest() throws Exception {

        int expected = 2;

        List<Event> result = eventController.showEvent(modelMap, expected);

        assertEquals(expected, result.size());

    }
}