package com.example.demo.locations;

import java.util.List;

public interface LocationsService {
    List<Locations> getAllLocations();
    Locations getLocationById(int id);
    Locations saveLocation(Locations location);
    Locations updateLocation(int id, Locations location);
    void deleteLocation(int id);
}
