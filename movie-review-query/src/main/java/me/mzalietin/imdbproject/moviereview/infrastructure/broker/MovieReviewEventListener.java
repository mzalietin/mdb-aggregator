package me.mzalietin.imdbproject.moviereview.infrastructure.broker;

import jakarta.validation.Valid;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.ResourceAlreadyExistsException;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.ResourceNotFoundException;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewCreated;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewDeleted;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewUpdated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    id = "movie-review-group",
    topics = "movie-review",
    batch = "false",
    clientIdPrefix = "MovieReviewConsumer"
)
public class MovieReviewEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieReviewEventListener.class);

    @Autowired
    MovieReviewDataAccess movieReviewDataAccess;

    @KafkaHandler
    public void onCreated(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, @Payload @Valid MovieReviewCreated value) {
        try {
            movieReviewDataAccess.create(new MovieReview(key.username(), key.movieId(), value.rating(), value.comment()));
        } catch (ResourceAlreadyExistsException e) {
            logger.error("MovieReviewEventListener - onCreated", e);
        }
    }

    @KafkaHandler
    public void onUpdated(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, @Payload @Valid MovieReviewUpdated value) {
        try {
            movieReviewDataAccess.update(new MovieReview(key.username(), key.movieId(), value.newRating(), value.newComment()));
        } catch (ResourceNotFoundException e) {
            logger.error("MovieReviewEventListener - onUpdated", e);
        }
    }

    @KafkaHandler
    public void onDeleted(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, MovieReviewDeleted value) {
        try {
            movieReviewDataAccess.delete(key);
        } catch (ResourceNotFoundException e) {
            logger.error("MovieReviewEventListener - onDeleted", e);
        }
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        logger.error("Unknown object received: {}", object);
    }
}
