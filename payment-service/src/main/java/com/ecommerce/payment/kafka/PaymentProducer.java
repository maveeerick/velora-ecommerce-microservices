package com.ecommerce.payment.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "payment-processed";

    public void sendPaymentEvent(Object event) {
        kafkaTemplate.send(TOPIC, event);
    }
}