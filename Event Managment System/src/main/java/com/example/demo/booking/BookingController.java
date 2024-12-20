package com.example.demo.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingServices bookingServices;

    // Add a new booking
    @PostMapping
    public ResponseEntity<BookingEntity> addNewBooking(@RequestBody BookingEntity booking) {
        try {
            BookingEntity savedBooking = bookingServices.addNewBooking(booking);
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        } catch (InvalidBookingException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a booking by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) {
        try {
            bookingServices.deleteBooking(id);
            return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
        } catch (BookingNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingEntity>> getAllBookings() {
        List<BookingEntity> bookings = bookingServices.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingEntity> getBookingById(@PathVariable int id) {
        try {
            BookingEntity booking = bookingServices.getBookingById(id);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (BookingNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get booking by Hall ID
    @GetMapping("/hall/{hallId}")
    public ResponseEntity<BookingEntity> getBookingByHallId(@PathVariable int hallId) {
        try {
            BookingEntity booking = bookingServices.getBookingByHallId(hallId);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (BookingNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Update a booking
    @PutMapping("/{id}")
    public ResponseEntity<BookingEntity> updateBooking(@PathVariable int id, @RequestBody BookingEntity booking) {
        try {
            BookingEntity updatedBooking = bookingServices.updateBooking(id, booking);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } catch (InvalidBookingException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (BookingNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
