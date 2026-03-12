package me.mzalietin.imdbproject.movierating.infrastructure.broker;

import jakarta.validation.Valid;
import me.mzalietin.imdbproject.movierating.domain.model.MovieReviewKey;
import me.mzalietin.imdbproject.movierating.infrastructure.broker.events.MovieReviewCreated;
import me.mzalietin.imdbproject.movierating.infrastructure.broker.events.MovieReviewDeleted;
import me.mzalietin.imdbproject.movierating.infrastructure.broker.events.MovieReviewUpdated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    id = "movie-rating-group",
    topics = "movie-review",
    batch = "false",
    clientIdPrefix = "MovieReviewRatingConsumer",
    containerFactory = "moviereviewKafkaListenerContainerFactory"
)
public class MovieReviewEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieReviewEventListener.class);

    @KafkaHandler
    public void onCreated(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, @Payload @Valid MovieReviewCreated value) {
        logger.info("Received MovieReviewCreated event for key={}, value={}", key, value);
    }

    @KafkaHandler
    public void onUpdated(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, @Payload @Valid MovieReviewUpdated value) {
        logger.info("Received MovieReviewUpdated event for key={}, value={}", key, value);
    }

    @KafkaHandler
    public void onDeleted(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, MovieReviewDeleted value) {
        logger.info("Received MovieReviewDeleted event for key={}, value={}", key, value);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        logger.info("Received unknown event for object={}", object);
    }
}
