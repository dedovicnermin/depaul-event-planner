package io.depaul.depauleventplanner.model;

import io.depaul.depauleventplanner.model.location.Location;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDetails {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String imageLocation;
    private Location location;

    public EventDetails(String name, LocalDateTime startDate, LocalDateTime endDate, String imageLocation, String description, Location location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageLocation = imageLocation;
        this.description = description;
        this.location = location;
    }

}
