package com.example.demo.locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationsService locationsService;

    @GetMapping
    public List<Locations> getAllLocations() {
        return locationsService.getAllLocations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locations> getLocationById(@PathVariable int id) {
        Locations location = locationsService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @PostMapping
    public ResponseEntity<Locations> createLocation(@RequestBody Locations location) {
        Locations savedLocation = locationsService.saveLocation(location);
        return ResponseEntity.ok(savedLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locations> updateLocation(@PathVariable int id, @RequestBody Locations location) {
        Locations updatedLocation = locationsService.updateLocation(id, location);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationsService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
