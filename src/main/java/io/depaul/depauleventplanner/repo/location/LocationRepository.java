package io.depaul.depauleventplanner.repo.location;

import io.depaul.depauleventplanner.model.location.Location;

import java.util.List;

public interface LocationRepository {
    Location getLocation(String id);
    void save(Location location);
    void addLocation(Location location);
    List<Location> all();
}
