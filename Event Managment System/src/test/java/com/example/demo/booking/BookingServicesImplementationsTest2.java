package com.example.demo.booking;

import static org.assertj.core.api.Assertions.*;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.SecondApplication;

@SpringBootTest(classes = SecondApplication.class)
class BookingServicesImplementationsTest2 { 

    @Mock
    private BookingRepo bookingRepo;

    @InjectMocks
    private BookingServicesImplementations bookingServices;

    @Test
    public void testAddNewBooking_Success() throws InvalidBookingException {
        BookingEntity booking = new BookingEntity();
        Mockito.when(bookingRepo.save(Mockito.any(BookingEntity.class))).thenReturn(booking);

        BookingEntity result = bookingServices.addNewBooking(booking);

        assertThat(result).isNotNull();
        Mockito.verify(bookingRepo, Mockito.times(1)).save(booking);
    }

    @Test  
    public void testAddNewBooking_NullBooking_ThrowsException() { 
        assertThatThrownBy(() -> {
            bookingServices.addNewBooking(null);
        }).isInstanceOf(InvalidBookingException.class);
    }

    @Test
    public void testDeleteBooking_Success() throws BookingNotFoundException {
        int bookingId = 1;
        Mockito.when(bookingRepo.existsById(bookingId)).thenReturn(true);

        bookingServices.deleteBooking(bookingId);

        Mockito.verify(bookingRepo, Mockito.times(1)).deleteById(bookingId);
    }

    @Test
    public void testDeleteBooking_NonExistent_ThrowsException() {
        int bookingId = 1;
        Mockito.when(bookingRepo.existsById(bookingId)).thenReturn(false);

        assertThatThrownBy(() -> {
            bookingServices.deleteBooking(bookingId);
        }).isInstanceOf(BookingNotFoundException.class);
    }
 
    @Test
    public void testGetAllBookings() {
        List<BookingEntity> bookings = List.of(new BookingEntity(), new BookingEntity());
        Mockito.when(bookingRepo.findAll()).thenReturn(bookings);

        List<BookingEntity> result = bookingServices.getAllBookings();

        assertThat(result).hasSize(2);
        Mockito.verify(bookingRepo, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetBookingById_Success() throws BookingNotFoundException {
        int bookingId = 1;
        BookingEntity booking = new BookingEntity();
        Mockito.when(bookingRepo.findById(bookingId)).thenReturn(Optional.of(booking));

        BookingEntity result = bookingServices.getBookingById(bookingId);

        assertThat(result).isNotNull();
        Mockito.verify(bookingRepo, Mockito.times(1)).findById(bookingId);
    }

    @Test
    public void testGetBookingById_NonExistent_ThrowsException() {
        int bookingId = 1;
        Mockito.when(bookingRepo.findById(bookingId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            bookingServices.getBookingById(bookingId);
        }).isInstanceOf(BookingNotFoundException.class);
    }

    @Test
    public void testUpdateBooking_Success() throws InvalidBookingException, BookingNotFoundException {
        int bookingId = 1;
        BookingEntity existingBooking = new BookingEntity();
        existingBooking.setId(bookingId); // Ensure IDs match

        BookingEntity updatedBooking = new BookingEntity();
        updatedBooking.setId(bookingId); // Ensure IDs match
        updatedBooking.setUserId(2);

        Mockito.when(bookingRepo.findById(bookingId)).thenReturn(Optional.of(existingBooking));
        Mockito.when(bookingRepo.save(Mockito.any(BookingEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookingEntity result = bookingServices.updateBooking(bookingId, updatedBooking);

        assertThat(result.getUserId()).isEqualTo(2);
        Mockito.verify(bookingRepo, Mockito.times(1)).save(existingBooking);
    }

    
 
    @Test
    public void testUpdateBooking_NonExistent_ThrowsException() {
        int bookingId = 1;
        BookingEntity updatedBooking = new BookingEntity();

        Mockito.when(bookingRepo.findById(bookingId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            bookingServices.updateBooking(bookingId, updatedBooking);
        }).isInstanceOf(BookingNotFoundException.class);
    }
}
