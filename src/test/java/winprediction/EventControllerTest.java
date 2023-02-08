package winprediction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import winprediction.controller.EventController;
import winprediction.model.Competitor;
import winprediction.model.Event;
import winprediction.model.Events;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
    public void showSortedCompetitorsTest() throws Exception {
        Class<Events> eventsClass = Events.class;
        Events events = new Events();
        Events expected = events.deserializeJson("src\\main\\resources\\json\\data.json", eventsClass);

        List<String> sortedCompetitorList = expected.getEvents().stream()
                .flatMap(event -> event.getCompetitors().stream().map(Competitor::getName))
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        List<String> result = eventController.showEvent(modelMap, 100)
                .stream()
                .flatMap(event -> event.getCompetitors().stream().map(Competitor::getName))
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        assertEquals(sortedCompetitorList, result);
    }

    @Test
    public void showEventTest() throws Exception {

        int expected = 2;

        List<Event> result = eventController.showEvent(modelMap, expected);

        assertEquals(expected, result.size());
    }

}