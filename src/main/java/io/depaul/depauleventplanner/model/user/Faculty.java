package io.depaul.depauleventplanner.model.user;

import io.depaul.depauleventplanner.behavior.Organizer;
import io.depaul.depauleventplanner.model.RegisteredEvent;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends User implements Organizer {

    public Faculty(String username, String password, String name) {
        super(username, password, name);
    }

    @Override
    public String displayName() {
        final String[] firstLast = getName().split(" ");
        return firstLast[0] + " " + firstLast[1].charAt(0) + ".";
    }

    @Override
    public UserType getUserType() {
        return UserType.FACULTY;
    }
}
