package com.example.demo.locations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class LocationsServiceImpTest {

    @Mock
    private LocationsRepository locationsRepository;

    @InjectMocks
    private LocationsServiceImp locationsService;

    private Locations location1;
    private Locations location2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize Location objects using setters
        location1 = new Locations();
        location1.setId(1);
        location1.setName("Location 1");
        location1.setDescription("Description 1");
        location1.setPrice(100);
        location1.setAddress("Address 1");
        location1.setState("State 1");
        location1.setZipCode("12345");

        location2 = new Locations();
        location2.setId(2);
        location2.setName("Location 2");
        location2.setDescription("Description 2");
        location2.setPrice(200);
        location2.setAddress("Address 2");
        location2.setState("State 2");
        location2.setZipCode("67890");
    }

    @Test
    void testGetAllLocations() {
        // Arrange
        List<Locations> locationsList = Arrays.asList(location1, location2);
        when(locationsRepository.findAll()).thenReturn(locationsList);

        // Act
        List<Locations> result = locationsService.getAllLocations();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Location 1", result.get(0).getName());
        assertEquals("Location 2", result.get(1).getName());
    }

    @Test
    void testGetLocationById() {
        // Arrange
        when(locationsRepository.findById(1)).thenReturn(Optional.of(location1));

        // Act
        Locations result = locationsService.getLocationById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Location 1", result.getName());
    }

    @Test
    void testGetLocationById_NotFound() {
        // Arrange
        when(locationsRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            locationsService.getLocationById(999);
        });
        assertEquals("Location not found for id: 999", exception.getMessage());
    }

    @Test
    void testSaveLocation() {
        // Arrange
        when(locationsRepository.save(location1)).thenReturn(location1);

        // Act
        Locations result = locationsService.saveLocation(location1);

        // Assert
        assertNotNull(result);
        assertEquals("Location 1", result.getName());
    }

    @Test
    void testUpdateLocation() {
        // Arrange
        Locations updatedLocation = new Locations();
        updatedLocation.setId(1); // Ensure the same ID
        updatedLocation.setName("Updated Location");
        updatedLocation.setDescription("Updated Description");
        updatedLocation.setPrice(150);
        updatedLocation.setAddress("Updated Address");
        updatedLocation.setState("Updated State");
        updatedLocation.setZipCode("54321");

        // Mock the repository to return the original location when queried by ID
        when(locationsRepository.findById(1)).thenReturn(Optional.of(location1));
        
        // Mock the repository to save the updated location
        when(locationsRepository.save(any(Locations.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Locations result = locationsService.updateLocation(1, updatedLocation);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Location", result.getName(), "Name should be updated");
        assertEquals("Updated Description", result.getDescription(), "Description should be updated");
        assertEquals(150, result.getPrice(), "Price should be updated");
        assertEquals("Updated Address", result.getAddress(), "Address should be updated");
        assertEquals("Updated State", result.getState(), "State should be updated");
        assertEquals("54321", result.getZipCode(), "Zip Code should be updated");
    }

    @Test 
    void testDeleteLocation() { 
        // Arrange
        doNothing().when(locationsRepository).deleteById(1);

        // Act
        locationsService.deleteLocation(1);

        // Assert
        verify(locationsRepository, times(1)).deleteById(1);
    }
}
