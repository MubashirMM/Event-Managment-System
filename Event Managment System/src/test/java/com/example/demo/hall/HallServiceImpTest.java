package com.example.demo.hall;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HallServiceImpTest {

    @Mock
    private HallRepository hallRepository;

    @InjectMocks
    private HallServiceImp hallService;

    public HallServiceImpTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllHallsTest() {
        Hall hall1 = new Hall();
        hall1.setHallId(1);
        hall1.setName("Hall A");

        Hall hall2 = new Hall();
        hall2.setHallId(2);
        hall2.setName("Hall B");

        when(hallRepository.findAll()).thenReturn(Arrays.asList(hall1, hall2));

        List<Hall> halls = hallService.getAllHalls();
        assertEquals(2, halls.size());
        verify(hallRepository, times(1)).findAll();
    }

    @Test
    void getHallByIdTest() {
        Hall hall = new Hall();
        hall.setHallId(1);
        hall.setName("Hall A");

        when(hallRepository.findById(1)).thenReturn(Optional.of(hall));

        Hall fetchedHall = hallService.getHallById(1);
        assertNotNull(fetchedHall);
        assertEquals("Hall A", fetchedHall.getName());
        verify(hallRepository, times(1)).findById(1);
    }

    @Test
    void addHallTest() {
        Hall hall = new Hall();
        hall.setName("Hall A");

        when(hallRepository.save(hall)).thenReturn(hall);

        Hall savedHall = hallService.addHall(hall);
        assertNotNull(savedHall);
        assertEquals("Hall A", savedHall.getName());
        verify(hallRepository, times(1)).save(hall);
    }

    @Test
    void updateHallTest() {
        Hall existingHall = new Hall();
        existingHall.setHallId(1);
        existingHall.setName("Hall A");

        Hall updatedHall = new Hall();
        updatedHall.setName("Updated Hall");

        when(hallRepository.findById(1)).thenReturn(Optional.of(existingHall));
        when(hallRepository.save(any(Hall.class))).thenReturn(existingHall);

        Hall result = hallService.updateHall(1, updatedHall);
        assertNotNull(result);
        assertEquals("Updated Hall", result.getName());
        verify(hallRepository, times(1)).findById(1);
        verify(hallRepository, times(1)).save(existingHall);
    }

    @Test
    void deleteHallTest() {
        int hallId = 1;
        doNothing().when(hallRepository).deleteById(hallId);

        hallService.deleteHall(hallId);
        verify(hallRepository, times(1)).deleteById(hallId);
    }
}
