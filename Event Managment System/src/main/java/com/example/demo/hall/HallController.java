package com.example.demo.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
public class HallController {

    @Autowired 
    private HallService hallService;

    @GetMapping
    public ResponseEntity<List<Hall>> getAllHalls() {
        List<Hall> halls = hallService.getAllHalls();
        return new ResponseEntity<>(halls, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable int id) {
        Hall hall = hallService.getHallById(id);
        return new ResponseEntity<>(hall, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hall> addHall(@RequestBody Hall hall) {
        Hall createdHall = hallService.addHall(hall);
        return new ResponseEntity<>(createdHall, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable int id, @RequestBody Hall hall) {
        Hall updatedHall = hallService.updateHall(id, hall);
        return new ResponseEntity<>(updatedHall, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable int id) {
        hallService.deleteHall(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
 