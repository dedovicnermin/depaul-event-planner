package io.depaul.depauleventplanner.config;

import io.depaul.depauleventplanner.model.EventDetails;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.location.Location;
import io.depaul.depauleventplanner.model.user.Faculty;
import io.depaul.depauleventplanner.model.user.Student;
import io.depaul.depauleventplanner.model.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class InitData {
    static final Location location1 = new Location(UUID.randomUUID().toString(), "Big Blue's Room","222 W TwoStreet", "Chi", "IL", 40);
    static final Location location2 = new Location(UUID.randomUUID().toString(), "McGee's Bar and Pub","121 W PubCity", "Chi", "IL", 30);
    static final Faculty FACULTY = new Faculty("slapass", "password", "Vicky Slapass");
    static final Student STUDENT = new Student("nermin", "password", "Nermin Dedovic");

    @Bean
    public Map<String, Location> locationMap() {
        Map<String, Location> map = new HashMap<>();
        map.put(location1.getId(), location1);
        map.put(location2.getId(), location2);
        return map;
    }

    @Bean
    public Map<String, RegisteredEvent> registeredEventMapInitial()
    {
        Map<String,RegisteredEvent> map = new HashMap<>();

        final RegisteredEvent kick_off = new RegisteredEvent(
                new EventDetails("Kick Off", LocalDateTime.now(), LocalDateTime.now().plusHours(3), "/images/event1.jpeg", "This is the first kick off event", location1),
                FACULTY
        );
        kick_off.addParticipant(STUDENT);
        final RegisteredEvent dance_off = new RegisteredEvent(
                new EventDetails("Dance off", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "/images/event2.jpeg", "Dance your butt off people. Dance your butt off people. Dance your butt off people. Dance your butt off people. Dance your butt off people. Dance your butt off people.", location2),
                FACULTY
        );
        dance_off.addParticipant(STUDENT);
        map.put(kick_off.getId(), kick_off);
        map.put(dance_off.getId(), dance_off);
        return map;
    }



    @Bean
    public Map<String, User> userMap() {
        Map<String,User> map = new HashMap<>();
        map.put(STUDENT.getUsername(), STUDENT);
        map.put(FACULTY.getUsername(), FACULTY);
        map.put("alex", new Student("alex", "password", "Alex Xela"));
        return map;
    }





}
