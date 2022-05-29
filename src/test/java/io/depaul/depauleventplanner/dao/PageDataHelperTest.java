package io.depaul.depauleventplanner.dao;

import io.depaul.depauleventplanner.config.auth.AppUserDetails;
import io.depaul.depauleventplanner.model.EventDetails;
import io.depaul.depauleventplanner.model.RegisteredEvent;
import io.depaul.depauleventplanner.model.user.Faculty;
import io.depaul.depauleventplanner.model.user.Student;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PageDataHelperTest {

    @Test
    void determineButtonOption() {
        final EventDetails eventDetails = new EventDetails(
                "Kick Off",
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                null,
                null
        );

        final Faculty faculty = new Faculty("lappas", "password", "Vicky Lappas");
        final Student student = new Student("nermin", "password", "Nermin Dedovic");
        final RegisteredEvent registeredEvent = new RegisteredEvent(eventDetails, faculty);
        registeredEvent.addParticipant(student);


        final PopupData actualPopupData = PageDataHelper.determineButtonOption(registeredEvent, new AppUserDetails(student));


        assertThat(registeredEvent.getParticipantCount()).isEqualTo("1");
        assertThat(actualPopupData).isEqualTo(PageDataHelper.cancelReservationPopup(registeredEvent.getId()));


    }
}