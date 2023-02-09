package winprediction.service;

import org.springframework.stereotype.Service;
import winprediction.model.Competitor;
import winprediction.model.Event;
import winprediction.model.Events;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortService implements EventInterface {

    public List<String> sortCompetitors(int numberOfMatchesToDisplay) throws Exception {
        Events events = new Events();
        List<Event> allEvents = events.getEvents(numberOfMatchesToDisplay);

        List<String> sortedCompetitorList = allEvents.stream()
                .flatMap(event -> event.getCompetitors().stream().map(Competitor::getName))
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return sortedCompetitorList;
    }
}
