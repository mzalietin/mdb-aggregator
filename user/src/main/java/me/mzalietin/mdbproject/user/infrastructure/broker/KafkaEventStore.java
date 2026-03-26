package me.mzalietin.mdbproject.user.infrastructure.broker;

import me.mzalietin.mdbproject.user.domain.model.User;
import me.mzalietin.mdbproject.user.domain.service.spi.EventStore;
import me.mzalietin.mdbproject.user.infrastructure.broker.events.UserCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventStore implements EventStore {

    @Autowired
    KafkaTemplate<String, Object> kt;

    @Value("${user.context.kafka.out.events-topic}")
    String eventsTopic;

    @Override
    public void sendCreated(final User user) {
        kt.send(eventsTopic, user.username(), new UserCreated(user.firstName(), user.lastName(), user.age()));
    }

    @Override
    public void sendDeleted(final String username) {
        kt.send(eventsTopic, username, null);
    }
}
