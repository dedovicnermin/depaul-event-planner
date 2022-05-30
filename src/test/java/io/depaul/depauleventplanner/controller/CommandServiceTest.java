package io.depaul.depauleventplanner.controller;

import io.depaul.depauleventplanner.model.EventDetails;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.location.Location;
import io.depaul.depauleventplanner.model.user.Faculty;
import io.depaul.depauleventplanner.model.user.Student;
import io.depaul.depauleventplanner.repo.event.RegisteredEventRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CommandServiceTest {

    CommandService service;
    Map<String, RegisteredEvent> initialState;
    Faculty FACULTY = new Faculty("user100", "pass", "user 100");
    Student STUDENT = new Student("user1", "paswers", "user 1");

    @BeforeEach
    void init() {
         initialState = new HashMap<>();
        final RegisteredEvent registeredEvent = new RegisteredEvent(new EventDetails("dsf", null, null, null, null, new Location(null, null, null, null,10)), FACULTY);
        registeredEvent.addParticipant(STUDENT);
        initialState.put(registeredEvent.getId(), registeredEvent);
        service = new CommandService(null, null, new RegisteredEventRepo(initialState));

    }

    @Test
    void getEventsLinkedToUserORGANIZER() {
        assertThat(service.getEventsLinkedToUser(FACULTY.getUsername())).hasSize(1);
    }

    @Test
    void getEventsLinkedToUserSTUDENT() {
        assertThat(service.getEventsLinkedToUser(STUDENT.getUsername())).hasSize(1);
    }


}