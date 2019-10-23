package api.location;

import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * The application is designed for Vehicle Geolocation:
 *
 * There is a large amount of vehicles (millions)
 * Each vehicle is equipped with a GPS tracker
 * The tracker periodically sends vehicle geolocation to server (about every ten seconds)
 * The task is to create a RESTful web service that will:
 * Receive current coordinates from vehicle GPS trackers
 * On request return the list of vehicles that are located within a given rectangle on the map.
 * The rectangle coordinates are provided as the request params
 */

@SpringBootApplication
public class Application {

  private static final String LOCALHOST = "localhost";
  private static final String TRACKS = "tracks";

  @Bean
  public MongoClient mongo() {
    return new MongoClient(LOCALHOST);
  }

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongo(), TRACKS);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
