package com.example.demo.payment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PaymentServiceImpTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImp paymentService;

    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        payment = new Payment();
        payment.setPaymentid(1);
        payment.setBookingId(101);
        payment.setMethodName("Credit Card");
        payment.setAmount(1500.00);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentStatus("Completed");
    }

    @Test
    void testSavePayment() {
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.savePayment(payment);

        assertNotNull(savedPayment);
        assertEquals(1, savedPayment.getPaymentid());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testGetPaymentById() {
        when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));

        Optional<Payment> fetchedPayment = paymentService.getPaymentById(1);

        assertTrue(fetchedPayment.isPresent());
        assertEquals(1, fetchedPayment.get().getPaymentid());
        verify(paymentRepository, times(1)).findById(1);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = Arrays.asList(payment);
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> allPayments = paymentService.getAllPayments();

        assertNotNull(allPayments);
        assertEquals(1, allPayments.size());
        verify(paymentRepository, times(1)).findAll();
    }
 
    @Test 
    void testDeletePaymentById() {
        doNothing().when(paymentRepository).deleteById(1);

        paymentService.deletePaymentById(1);

        verify(paymentRepository, times(1)).deleteById(1);
    }
}
 