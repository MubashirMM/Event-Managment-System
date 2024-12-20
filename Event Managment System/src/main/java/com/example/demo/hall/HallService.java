package com.example.demo.hall;

import java.util.List;

public interface HallService {
    List<Hall> getAllHalls();
    Hall getHallById(int hallId);
    Hall addHall(Hall hall);
    Hall updateHall(int hallId, Hall hall);
    void deleteHall(int hallId);
}
