package winprediction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Events implements JsonDeserializer {
    @JsonProperty("Events")
    private List<Event> events;
}
