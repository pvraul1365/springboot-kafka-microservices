package net.javaguides.orderservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * OrderProducer
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 01/09/2026 - 19:07
 * @since 1.17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final NewTopic newTopic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendMessage(final OrderEvent orderEvent) {
        log.info(String.format("Order event => %s", orderEvent.toString()));

        // create message and send to kafka topic
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();

        kafkaTemplate.send(message);
    }

}
