package net.javaguides.stockservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.basedomains.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * OrderConsumer
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 02/09/2026 - 19:27
 * @since 1.17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(final OrderEvent orderEvent) {
        log.info(String.format("Order event received in stock service => %s", orderEvent));

        // save the order event to the database or perform any other necessary actions
    }

}
