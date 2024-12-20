//package com.example.demo.booking;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer; 
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
// 
//@SpringBootTest(classes = com.example.demo.SecondApplication.class) // Replace 'YourMainApplicationClass' with the actual class name
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class BookingServicesImplementationsTest {
//
//    @Autowired
//    private BookingServices bookingServices; // Real service
//
//    @Autowired
//    private BookingRepo bookingRepo; // Real repository
//
//    @BeforeEach
//    public void setUp() {
//        bookingRepo.deleteAll(); // Clear database before each test
//    }
//
//    @Test
//    @Order(1)
//    public void shouldAddNewBookingSuccessfully() throws InvalidBookingException {
//        // Arrange
//        BookingEntity booking = new BookingEntity();
//        booking.setUserId(1);
//        booking.setHallId(2);
//        booking.setLocationId(3); 
//        booking.setFoodId(4);
//        booking.setDrinkId(5);
//        booking.setTotalCost(1000);
//        booking.setNoOfPersons(50);
//        booking.setEventDate(LocalDate.now());
//        booking.setBookingDate(LocalDate.now());
//
//        // Act
//        BookingEntity savedBooking = bookingServices.addNewBooking(booking);
//
//        // Assert
//        assertNotNull(savedBooking.getId(), "Booking ID should not be null after saving.");
//        assertEquals(1, bookingRepo.count(), "There should be one booking in the repository.");
//    }
//
//    @Test
//    @Order(2)
//    public void shouldGetAllBookingsSuccessfully() {
//        // Arrange
//        BookingEntity booking1 = new BookingEntity();
//        booking1.setUserId(1);
//        booking1.setHallId(2);
//        bookingRepo.save(booking1);
//
//        BookingEntity booking2 = new BookingEntity();
//        booking2.setUserId(2);
//        booking2.setHallId(3);
//        bookingRepo.save(booking2);
//
//        // Act
//        List<BookingEntity> bookings = bookingServices.getAllBookings();
//
//        // Assert
//        assertEquals(2, bookings.size(), "There should be two bookings retrieved.");
//    }
//
//    @Test
//    @Order(3)
//    public void shouldDeleteBookingSuccessfully() throws BookingNotFoundException {
//        // Arrange
//        BookingEntity booking = new BookingEntity();
//        booking.setUserId(1);
//        booking.setHallId(2);
//        BookingEntity savedBooking = bookingRepo.save(booking);
//
//        // Act
//        bookingServices.deleteBooking(savedBooking.getId());
//
//        // Assert
//        assertEquals(0, bookingRepo.count(), "The booking repository should be empty after deletion.");
//    }
//
//    @Test
//    @Order(4)
//    public void shouldGetBookingByIdSuccessfully() throws BookingNotFoundException {
//        // Arrange
//        BookingEntity booking = new BookingEntity();
//        booking.setUserId(1);
//        booking.setHallId(2);
//        BookingEntity savedBooking = bookingRepo.save(booking);
//
//        // Act
//        BookingEntity foundBooking = bookingServices.getBookingById(savedBooking.getId());
//
//        // Assert
//        assertNotNull(foundBooking, "Booking should be retrieved successfully.");
//        assertEquals(savedBooking.getId(), foundBooking.getId(), "Retrieved booking ID should match the saved booking ID.");
//    }
//
//    @Test
//    @Order(5)
//    public void shouldUpdateBookingSuccessfully() throws InvalidBookingException, BookingNotFoundException {
//        // Arrange
//        BookingEntity booking = new BookingEntity();
//        booking.setUserId(1);
//        booking.setHallId(2);
//        BookingEntity savedBooking = bookingRepo.save(booking);
//
//        BookingEntity updatedBooking = new BookingEntity();
//        updatedBooking.setUserId(5); // New userId to update
//        updatedBooking.setHallId(3); // New hallId to update
//
//        // Act
//        BookingEntity result = bookingServices.updateBooking(savedBooking.getId(), updatedBooking);
//
//        // Assert
//        assertEquals(5, result.getUserId(), "User ID should be updated.");
//        assertEquals(3, result.getHallId(), "Hall ID should be updated.");
//    }
//}
