package winprediction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Competitor {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String country;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("abbreviation")
    private String abbreviation;

    @JsonProperty("qualifier")
    private String qualifier;

    @JsonProperty("gender")
    private String gender;
}