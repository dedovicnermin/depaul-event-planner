package io.depaul.depauleventplanner.config;

import io.depaul.depauleventplanner.model.EventDetails;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.location.Location;
import io.depaul.depauleventplanner.model.location.LocationStatus;
import io.depaul.depauleventplanner.model.user.Faculty;
import io.depaul.depauleventplanner.model.user.Student;
import io.depaul.depauleventplanner.model.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class InitData {
    static final Location location1 = new Location( "Big Blue's Room","222 W TwoStreet", "Chi", "IL", 40);
    static final Location location2 = new Location("McGee's Bar and Pub","121 W PubCity", "Chi", "IL", 30);
    static final Location location3 = new Location("The Library", "234 DPUACADEMIA ST", "Chi", "IL", 122);
    static final Faculty FACULTY = new Faculty("faculty", "password", "Faculty Member");
    static final Student STUDENT1 = new Student("nermin", "password", "Nermin D");
    static final Student STUDENT2 = new Student("george", "password", "George F");
    static final Student STUDENT3 = new Student("alejandro", "password", "Alejandro Z");


    @Bean
    public Map<String, Location> locationMap() {
        Map<String, Location> map = new HashMap<>();
        map.put(location1.getId(), location1);
        map.put(location2.getId(), location2);
        map.put(location3.getId(), location3);
        return map;
    }

    @Bean
    public Map<String, RegisteredEvent> registeredEventMapInitial()
    {
        Map<String,RegisteredEvent> map = new HashMap<>();

        final RegisteredEvent kick_off = new RegisteredEvent(
                new EventDetails("Kick Off", LocalDateTime.now(), LocalDateTime.now().plusHours(3),"This is the first kick off event", "/images/event1.jpeg", location1),
                FACULTY
        );
        location1.setStatus(LocationStatus.RESERVED);
        kick_off.addParticipant(STUDENT1);
        final RegisteredEvent dance_off = new RegisteredEvent(
                new EventDetails("Dance off", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Dance your butt off people. Dance your butt off people. Dance your butt off people. Dance your butt off people. Dance your butt off people. Dance your butt off people.", "/images/event2.jpeg", location2),
                FACULTY
        );
        location2.setStatus(LocationStatus.RESERVED);
        dance_off.addParticipant(STUDENT1);
        map.put(kick_off.getId(), kick_off);
        map.put(dance_off.getId(), dance_off);
        return map;
    }


    @Bean
    public Map<String, User> userMap() {
        Map<String,User> map = new HashMap<>();
        map.put(STUDENT1.getUsername(), STUDENT1);
        map.put(STUDENT2.getUsername(), STUDENT2);
        map.put(STUDENT3.getUsername(), STUDENT3);
        map.put(FACULTY.getUsername(), FACULTY);
        return map;
    }





}
