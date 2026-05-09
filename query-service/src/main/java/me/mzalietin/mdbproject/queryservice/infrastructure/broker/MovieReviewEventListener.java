package me.mzalietin.mdbproject.queryservice.infrastructure.broker;

import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewDeleted;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewKey;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewUpdated;
import me.mzalietin.mdbproject.queryservice.domain.service.spi.WriteOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    id = "query-service-review-context-group",
    topics = "${query.service.kafka.in.review-events-topic}",
    batch = "false",
    clientIdPrefix = "QueryServiceReviewConsumer"
)
public class MovieReviewEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieReviewEventListener.class);

    @Autowired
    WriteOperations writeOperations;

    @KafkaHandler
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) String key, @Payload ReviewCreated event, Acknowledgment ack) {
        logger.info("Received event for key={}, event={}", key, event);
        writeOperations.createReview(key, event);
        ack.acknowledge();
    }

    @KafkaHandler
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) String key, @Payload ReviewUpdated event, Acknowledgment ack) {
        logger.info("Received event for key={}, event={}", key, event);
        writeOperations.updateReview(key, event);
        ack.acknowledge();
    }

    @KafkaHandler
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) String key, @Payload ReviewDeleted event, Acknowledgment ack) {
        logger.info("Received event for key={}, event={}", key, event);
        writeOperations.deleteReview(key);
        ack.acknowledge();
    }
}
