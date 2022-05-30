package io.depaul.depauleventplanner.model;

import io.depaul.depauleventplanner.model.location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class EventDetails {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String imageLocation;
    private Location location;



}
