package com.example.demo.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImp implements HallService {

    @Autowired
    private HallRepository hallRepository;

    @Override
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public Hall getHallById(int hallId) {
        Optional<Hall> hall = hallRepository.findById(hallId);
        return hall.orElseThrow(() -> new RuntimeException("Hall not found with id: " + hallId));
    }

    @Override
    public Hall addHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public Hall updateHall(int hallId, Hall hall) {
        Hall existingHall = getHallById(hallId);
        existingHall.setName(hall.getName());
        existingHall.setPrice(hall.getPrice());
        existingHall.setCapacity(hall.getCapacity());
        existingHall.setDescription(hall.getDescription());
        existingHall.setLocationId(hall.getLocationId());
        return hallRepository.save(existingHall);
    }

    @Override
    public void deleteHall(int hallId) {
        hallRepository.deleteById(hallId);
    }
}
