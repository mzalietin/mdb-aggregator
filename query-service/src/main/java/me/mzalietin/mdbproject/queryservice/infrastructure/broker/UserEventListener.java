package me.mzalietin.mdbproject.queryservice.infrastructure.broker;

import me.mzalietin.mdbproject.queryservice.domain.model.event.UserCreated;
import me.mzalietin.mdbproject.queryservice.domain.service.spi.WriteOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    id = "query-service-user-context-group",
    topics = "${query.service.kafka.in.user-events-topic}",
    batch = "false",
    clientIdPrefix = "QueryServiceUserConsumer"
)
public class UserEventListener {

    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @Autowired
    WriteOperations writeOperations;

    @KafkaHandler
    public void onCreated(@Header(KafkaHeaders.RECEIVED_KEY) String username, @Payload UserCreated value, Acknowledgment ack) {
        logger.info("Received user created event username={}", username);
        writeOperations.createUser(username, value);
        ack.acknowledge();
    }

    @KafkaHandler(isDefault = true)
    public void onDeleted(@Header(KafkaHeaders.RECEIVED_KEY) String username, @Payload(required = false) KafkaNull nul, Acknowledgment ack) {
        logger.info("Received user deleted event username={}", username);
        writeOperations.deleteUser(username);
        ack.acknowledge();
    }
}
