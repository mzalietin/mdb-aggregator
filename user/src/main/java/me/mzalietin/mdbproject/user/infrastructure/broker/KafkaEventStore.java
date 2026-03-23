package me.mzalietin.mdbproject.user.infrastructure.broker;

import me.mzalietin.mdbproject.user.domain.service.spi.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KafkaEventStore implements EventStore {

    @Autowired
    KafkaTemplate<String, Object> kt;

    @Value("${user.context.kafka.out.events-topic}")
    String eventsTopic;

    @Override
    @Transactional("kafkaTransactionManager")
    public void sendDeleted(final String username) {
        kt.executeInTransaction(kt -> {
            kt.send(eventsTopic, username, null);
            return true;
        });
    }
}
