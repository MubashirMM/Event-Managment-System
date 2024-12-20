package com.example.demo.booking;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, Integer> {
    // You can add custom query methods here if needed
	Optional<BookingEntity> findByhallId(int hallId);
}
 