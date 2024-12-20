package com.example.demo.payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment savePayment(Payment payment);

    Optional<Payment> getPaymentById(int paymentId);

    List<Payment> getAllPayments();

    void deletePaymentById(int paymentId);
}
 