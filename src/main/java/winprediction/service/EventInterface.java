package winprediction.service;

import winprediction.model.Event;
import winprediction.model.Events;

import java.util.List;

public interface EventInterface extends JsonDeserializer {
    default List<Event> getEvents(int numerOfMatchesToDisplay) throws Exception{
        Events events = new Events();
        Class<Events> eventsClass = Events.class;
        Events mappedEvents = events.deserializeJson("src\\main\\resources\\json\\data.json", eventsClass);
        List<Event> eventList = mappedEvents.getEvents();
        return eventList;
    }
}
