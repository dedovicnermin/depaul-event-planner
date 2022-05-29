package io.depaul.depauleventplanner.repo.location;

import io.depaul.depauleventplanner.model.location.Location;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LocationRepo implements LocationRepository {
    private final Map<String, Location> locationMap;
    public LocationRepo() {
        locationMap = new HashMap<>();
    }

    @Override
    public Location getLocation(String id) {
        return locationMap.get(id);
    }

    @Override
    public void save(Location location) {
        locationMap.put(location.getId(), location);
    }

    @Override
    public void addLocation(Location location) {
        locationMap.put(UUID.randomUUID().toString(), location);
    }

    @Override
    public List<Location> all() {
        return null;
    }
}
