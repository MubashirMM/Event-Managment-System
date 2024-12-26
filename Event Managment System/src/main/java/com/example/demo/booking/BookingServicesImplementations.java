package com.example.demo.booking;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class BookingServicesImplementations implements BookingServices {
     
    @Autowired 
    private BookingRepo repo;

    @Override 
    public BookingEntity addNewBooking(BookingEntity booking) throws InvalidBookingException {
        if (booking == null) {
            throw new InvalidBookingException("Booking cannot be null");
        }
        return repo.save(booking);
    }

    @Override 
    public void deleteBooking(int id) throws BookingNotFoundException { 
        if (!repo.existsById(id)) {
            throw new BookingNotFoundException("No booking with id " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public List<BookingEntity> getAllBookings() {
        return repo.findAll();
    }

    @Override 
    public BookingEntity getBookingById(int id) throws BookingNotFoundException{
        return repo.findById(id).orElseThrow(() ->
        new BookingNotFoundException("No hall with id " +id)); 
    } 
    
    @Override
    public BookingEntity getBookingByHallId(int id) throws BookingNotFoundException{
        return repo.findByhallId(id).orElseThrow(() -> 
            new BookingNotFoundException("No hall with id " +id)); 
    }
  
     
    @Override
    public BookingEntity updateBooking(int id, BookingEntity booking) throws InvalidBookingException, BookingNotFoundException {
        if (booking == null) {
            throw new InvalidBookingException("Booking cannot be null"); 
        }
        return repo.findById(id)
            .map(existingBooking -> {
                existingBooking.setUserId(booking.getUserId());
                existingBooking.setHallId(booking.getHallId());
                existingBooking.setLocationId(booking.getLocationId());
                existingBooking.setFoodId(booking.getFoodId());
                existingBooking.setDrinkId(booking.getDrinkId());
                existingBooking.setTotalCost(booking.getTotalCost());
                existingBooking.setEventDate(booking.getEventDate());
                existingBooking.setNoOfPersons(booking.getNoOfPersons()); 
                existingBooking.setBookingDate(booking.getBookingDate());
                return repo.save(existingBooking);
            }).orElseThrow(() -> new BookingNotFoundException("Booking not found with id " + id));
    }
}
