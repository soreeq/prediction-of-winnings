package winprediction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import winprediction.service.EventInterface;
import winprediction.service.JsonDeserializer;

import java.util.List;

@Data
public class Events implements EventInterface {
    @JsonProperty("Events")
    private List<Event> events;
}
