package me.mzalietin.mdbproject.queryservice.infrastructure.broker;

import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieRatingUpdated;
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
    id = "query-service-movie-context-group",
    topics = "${query.service.kafka.in.movie-events-topic}",
    batch = "false",
    clientIdPrefix = "QueryServiceMovieConsumer"
)
public class MovieEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieEventListener.class);

    @Autowired
    WriteOperations writeOperations;

    @KafkaHandler
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) Long movieId, @Payload MovieCreated event, Acknowledgment ack) {
        logger.info("Received event for movieId={} event={}", movieId, event);
        writeOperations.createMovie(movieId, event);
        ack.acknowledge();
    }

    @KafkaHandler
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) Long movieId, @Payload MovieRatingUpdated event, Acknowledgment ack) {
        logger.info("Received event for movieId={} event={}", movieId, event);
        writeOperations.updateMovie(movieId, event);
        ack.acknowledge();
    }
}
