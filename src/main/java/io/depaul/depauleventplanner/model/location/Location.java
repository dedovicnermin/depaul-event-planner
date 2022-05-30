package io.depaul.depauleventplanner.model.location;

import lombok.Data;

import java.util.UUID;

@Data
public class Location {
    private final String id;
    private final String name;
    private final String address;
    private final String city;
    private final String state;
    private int maxCapacity;
    LocationStatus status;

    public Location(String name, String address, String city, String state, int maxCapacity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.maxCapacity = maxCapacity;
        this.status = LocationStatus.AVAILABLE;
    }


}
