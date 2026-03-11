package me.mzalietin.imdbproject.moviereview.infrastructure.broker;

import me.mzalietin.imdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewEventListener {

    @Autowired
    MovieReviewDataAccess movieReviewDataAccess;

    @KafkaListener(
        id = "movie-review-group",
        topics = "movie-review",
        batch = "false",
        clientIdPrefix = "MovieReviewEventListener"
    )
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, @Payload(required = false) MovieReview value) {

        if (value == null) {
            // tombstone received - delete
            if (key != null) {
                movieReviewDataAccess.delete(key);
            }
        } else {
            // do upsert
            movieReviewDataAccess.save(value);
        }
    }
}
