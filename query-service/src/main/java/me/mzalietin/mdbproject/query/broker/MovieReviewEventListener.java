package me.mzalietin.mdbproject.query.broker;

import me.mzalietin.mdbproject.query.broker.event.ReviewCreated;
import me.mzalietin.mdbproject.query.broker.event.ReviewDeleted;
import me.mzalietin.mdbproject.query.broker.event.ReviewKey;
import me.mzalietin.mdbproject.query.broker.event.ReviewUpdated;
import me.mzalietin.mdbproject.query.repo.QueryServiceDaoFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

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
    QueryServiceDaoFacade queryServiceDaoFacade;

    @KafkaHandler
    public Mono<?> listen(@Header(KafkaHeaders.RECEIVED_KEY) ReviewKey key, @Payload ReviewCreated event) {
        logger.info("Received event for key={}, event={}", key, event);
        return queryServiceDaoFacade.movieReviewDao().save(key, event);
    }

    @KafkaHandler
    public Mono<?> listen(@Header(KafkaHeaders.RECEIVED_KEY) ReviewKey key, @Payload ReviewUpdated event) {
        logger.info("Received event for key={}, event={}", key, event);
        return queryServiceDaoFacade.movieReviewDao().save(key, event);
    }

    @KafkaHandler
    public Mono<?> listen(@Header(KafkaHeaders.RECEIVED_KEY) ReviewKey key, @Payload ReviewDeleted event) {
        logger.info("Received event for key={}, event={}", key, event);
        return queryServiceDaoFacade.movieReviewDao().save(key, event);
    }
}
