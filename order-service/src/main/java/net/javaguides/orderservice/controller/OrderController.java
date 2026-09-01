package net.javaguides.orderservice.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.javaguides.basedomains.dto.Order;
import net.javaguides.basedomains.dto.OrderEvent;
import net.javaguides.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 01/09/2026 - 19:36
 * @since 1.17
 */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;

    // Add your endpoints here
    @PostMapping
    public String placeOrder(@RequestBody final Order order) {
        // Logic to place order and send event to Kafka
        order.setOrderId(UUID.randomUUID().toString()); // Generate a unique order ID

        OrderEvent orderEvent = OrderEvent.builder()
                .order(order)
                .status("PENDING")
                .message("Order status is PENDING")
                .build();

        orderProducer.sendMessage(orderEvent);

        return "Order placed successfully!";
    }

}
