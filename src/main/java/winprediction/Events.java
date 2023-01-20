package winprediction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Events {
    @JsonProperty("Events")
    private List<Event> events;
}
