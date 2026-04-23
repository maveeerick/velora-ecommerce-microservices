package com.ecommerce.shipping.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "shipping-created";

    public void sendShippingEvent(Object event) {
        kafkaTemplate.send(TOPIC, event);
    }
}