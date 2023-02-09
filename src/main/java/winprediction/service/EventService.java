package winprediction.service;

import org.springframework.stereotype.Service;
import winprediction.model.Event;
import winprediction.model.Events;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements EventInterface {

//    @Override
//    public List<Event> getEvents(int numberOfMatchesToDisplay) throws Exception {
//        Events events = new Events();
//        Class<Events> eventsClass = Events.class;
//        Events mappedEvents = events.deserializeJson("src\\main\\resources\\json\\data.json", eventsClass);
//        List<Event> eventList = mappedEvents.getEvents();
//        return eventList;
//    }
    public List<Event> displayEvents(int numberOfMatchesToDisplay) throws Exception {
        Events events = new Events();
        List<Event> allEvents = events.getEvents(numberOfMatchesToDisplay);
        int actualLimit = Math.min(numberOfMatchesToDisplay, allEvents.size());
        List<Event> eventsToDisplay = allEvents.subList(0, actualLimit);

        eventsToDisplay.stream()
                .limit(Math.max(numberOfMatchesToDisplay, allEvents.size()))
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

                    } else {
                        event.setWillProbablyWin(awayTeam);
                        event.setHighestProbability(awayTeamProb);
                    }
                    return event;
                }).collect(Collectors.toList());
        return eventsToDisplay;
    }
}
