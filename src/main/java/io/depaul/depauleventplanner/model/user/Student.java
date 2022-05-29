package io.depaul.depauleventplanner.model.user;


import io.depaul.depauleventplanner.behavior.Participant;
import io.depaul.depauleventplanner.model.RegisteredEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Student extends User implements Participant {

    public Student(String username, String password, String name)
    {
        super(username, password, name);
    }
    @Override
    public String displayName() {
        return getName().split(" ")[0];
    }

    @Override
    public UserType getUserType() {
        return UserType.STUDENT;
    }
}
