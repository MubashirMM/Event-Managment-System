package com.example.demo.booking;

import java.util.List; 

public interface BookingServices { 
    BookingEntity addNewBooking(BookingEntity booking) throws InvalidBookingException;
    void deleteBooking(int id) throws BookingNotFoundException;
    List<BookingEntity> getAllBookings();
    BookingEntity getBookingById(int id) throws BookingNotFoundException;
    BookingEntity updateBooking(int id, BookingEntity updatedBooking) throws InvalidBookingException, BookingNotFoundException;
	BookingEntity getBookingByHallId(int id) throws BookingNotFoundException;
}



