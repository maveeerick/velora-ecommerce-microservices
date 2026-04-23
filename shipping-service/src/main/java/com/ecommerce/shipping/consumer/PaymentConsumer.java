package com.ecommerce.shipping.consumer;

import com.ecommerce.shipping.service.ShippingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final ShippingService shippingService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "payment-processed", groupId = "shipping-group")
    public void consumePayment(String message) {

        try {
            System.out.println("📥 PAYMENT RECEIVED: " + message);

            Map<String, Object> event = objectMapper.readValue(message, Map.class);

            Long orderId = Long.valueOf(event.get("orderId").toString());

            shippingService.processShipping(orderId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}