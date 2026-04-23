package com.ecommerce.shipping.service;

import com.ecommerce.shipping.entity.Shipping;
import com.ecommerce.shipping.kafka.ShippingProducer;
import com.ecommerce.shipping.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository shippingRepository;
    private final ShippingProducer shippingProducer;

    public void processShipping(Long orderId) {

        Shipping shipping = Shipping.builder()
                .orderId(orderId)
                .address("Default Address")
                .status("SHIPPED")
                .build();

        Shipping saved = shippingRepository.save(shipping);

        System.out.println("📦 SHIPPING CREATED: " + saved);

        shippingProducer.sendShippingEvent(saved);
    }
}