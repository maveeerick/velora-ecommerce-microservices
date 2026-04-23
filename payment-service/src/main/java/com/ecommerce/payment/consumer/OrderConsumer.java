package com.ecommerce.payment.consumer;

import com.ecommerce.payment.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "order-created", groupId = "payment-group-v3")
    public void consumeOrder(String message) {

        try {
            System.out.println("📥 RAW MESSAGE: " + message);

            Map<String, Object> event = objectMapper.readValue(message, Map.class);

            Long orderId = Long.valueOf(event.get("id").toString());
            Double amount = Double.valueOf(event.get("totalPrice").toString());

            paymentService.processPayment(orderId, amount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}