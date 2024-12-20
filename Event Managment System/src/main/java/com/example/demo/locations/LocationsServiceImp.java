package com.example.demo.locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationsServiceImp implements LocationsService {

    @Autowired
    private LocationsRepository locationsRepository;

    @Override
    public List<Locations> getAllLocations() { 
        return locationsRepository.findAll();
    }

    @Override
    public Locations getLocationById(int id) {
        Optional<Locations> location = locationsRepository.findById(id);
        return location.orElseThrow(() -> new RuntimeException("Location not found for id: " + id));
    }

    @Override
    public Locations saveLocation(Locations location) {
        return locationsRepository.save(location);
    }

    @Override
    public Locations updateLocation(int id, Locations updatedLocation) {
        Locations existingLocation = locationsRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Location not found for id: " + id));
        
        // Update fields
        existingLocation.setName(updatedLocation.getName());
        existingLocation.setDescription(updatedLocation.getDescription());
        existingLocation.setPrice(updatedLocation.getPrice());
        existingLocation.setAddress(updatedLocation.getAddress());
        existingLocation.setState(updatedLocation.getState());
        existingLocation.setZipCode(updatedLocation.getZipCode());
        
        return locationsRepository.save(existingLocation);
    }

    @Override
    public void deleteLocation(int id) {
        locationsRepository.deleteById(id);
    }
}
