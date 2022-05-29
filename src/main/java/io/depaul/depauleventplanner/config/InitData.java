package io.depaul.depauleventplanner.config;

import io.depaul.depauleventplanner.model.EventDetails;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.location.Location;
import io.depaul.depauleventplanner.model.user.Student;
import io.depaul.depauleventplanner.model.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class InitData {

    @Bean
    public Map<String, Location> locationMap() {
        Map<String, Location> map = new HashMap<>();
        final String key = UUID.randomUUID().toString();
        final Location location = new Location(key, "big building","222 W TwoStreet", "Chi", "IL", 40);
        map.put(key, location);
        return map;
    }

    @Bean
    public Map<String, RegisteredEvent> registeredEventMapInitial()
    {
        Map<String,RegisteredEvent> map = new HashMap<>();
        final String id = UUID.randomUUID().toString();
        final Location eventLocation = locationMap().values().stream().findFirst().orElseThrow();
        map.put(id, new RegisteredEvent(
                new EventDetails("Kick Off", LocalDateTime.now(), LocalDateTime.now().plusDays(1), "/images/event1.jpeg", "This is the first kick off event", eventLocation),
                null
        ));
        return map;
    }

    @Bean
    public Map<String, User> userMap() {
        Map<String,User> map = new HashMap<>();
        map.put("nermin", new Student("nermin", "password", "Nermin Dedovic"));
        return map;
    }





}
