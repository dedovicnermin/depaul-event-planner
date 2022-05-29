package io.depaul.depauleventplanner.model.user;


import io.depaul.depauleventplanner.behavior.Attendee;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class User implements Attendee {
    private final String username;
    private final String password;
    private final String name;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }


    public abstract UserType getUserType();



}


