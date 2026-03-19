package me.mzalietin.mdbproject.moviereview.infrastructure.broker;

import jakarta.validation.Valid;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.MovieReviewCreated;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.MovieReviewDeleted;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.MovieReviewUpdated;
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
    id = "movie-review-context-group",
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
        movieReviewDataAccess.create(new MovieReview(key.username(), key.movieId(), value.rating(), value.comment()));
    }

    @KafkaHandler
    public void onUpdated(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, @Payload @Valid MovieReviewUpdated value) {
        movieReviewDataAccess.update(new MovieReview(key.username(), key.movieId(), value.newRating(), value.newComment()));
    }

    @KafkaHandler
    public void onDeleted(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, MovieReviewDeleted value) {
        movieReviewDataAccess.delete(key);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        logger.error("Unknown object received: {}", object);
    }
}
