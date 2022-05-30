package io.depaul.depauleventplanner.repo.location;

import io.depaul.depauleventplanner.model.location.Location;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LocationRepo implements LocationRepository {
    private final Map<String, Location> locationMap;
    public LocationRepo(Map<String, Location> locationMap) {
        this.locationMap = locationMap;
    }

    @Override
    public Location getLocation(String id) {
        return locationMap.get(id);
    }

    @Override
    public Location getLocationByName(String locationName) {
        return locationMap.values().stream()
                .filter(location -> location.getName().equals(locationName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No location with this name :" + locationName));
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

        final ArrayList<Location> locations = new ArrayList<>(locationMap.values());
        System.out.println(locations);
        return locations;
    }


}
