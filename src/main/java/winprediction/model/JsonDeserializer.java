package winprediction.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface JsonDeserializer {
    default Events deserializeJson(String filePath, Class<Events> events) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
        String jsonString = new String(jsonData);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(jsonString, Events.class);
        } catch (IOException e) {
            throw new IOException("Error during deserialize JSON data");
        }
    }
}