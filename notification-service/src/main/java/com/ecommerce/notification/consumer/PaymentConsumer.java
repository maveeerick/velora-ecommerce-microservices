package com.ecommerce.notification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "payment-processed", groupId = "notification-group")
    public void consumePayment(String message) {

        try {
            System.out.println("📥 PAYMENT EVENT RECEIVED: " + message);

            Map<String, Object> event = objectMapper.readValue(message, Map.class);

            Long orderId = Long.valueOf(event.get("orderId").toString());
            Double amount = Double.valueOf(event.get("amount").toString());

            System.out.println("🔔 NOTIFICATION: Payment sukses untuk Order ID " 
                + orderId + " sebesar " + amount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}