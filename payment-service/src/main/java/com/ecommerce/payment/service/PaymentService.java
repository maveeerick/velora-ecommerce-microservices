package com.ecommerce.payment.service;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.kafka.PaymentProducer;
import com.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;

    public void processPayment(Long orderId, Double amount) {

        Payment payment = Payment.builder()
                .orderId(orderId)
                .amount(amount)
                .status("PAID")
                .build();

        Payment saved = paymentRepository.save(payment);

        System.out.println("💰 PAYMENT SUCCESS: " + saved);

        // 🔥 KIRIM KE KAFKA
        paymentProducer.sendPaymentEvent(saved);
    }
}