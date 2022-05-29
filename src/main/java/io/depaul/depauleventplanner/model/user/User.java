package io.depaul.depauleventplanner.model.user;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class User {
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


