package io.depaul.depauleventplanner.dao;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class RegisterEventForm {
    private String eventTitle;
    private String locationName;
    private String startDate;
    private String endDate;
    private String description;
    private String imgLocation;

    public String getEventTitle() {
        return eventTitle == null ? "" : eventTitle;
    }

    public String getLocationName() {
        return locationName == null ? "" : locationName;
    }

    public String getStartDate() {
        return startDate == null ? "" : startDate;
    }

    public String getEndDate() {
        return endDate == null ? "" : endDate;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public String getImgLocation() {
        return imgLocation == null ? "" : imgLocation;
    }
}
