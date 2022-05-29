package io.depaul.depauleventplanner.model.user;


import io.depaul.depauleventplanner.behavior.Participant;

import java.util.*;

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
