package me.mzalietin.imdbproject.moviereview.infrastructure.broker;

import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewCreatedEvent;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewDeletedEvent;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    id = "movie-review-group",
    topics = "movie-review",
    batch = "false",
    clientIdPrefix = "MovieReviewEventListener"
)
public class MovieReviewEventListener {

    @Autowired
    MovieReviewDataAccess movieReviewDataAccess;

    @KafkaHandler
    public void listenCreated(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, MovieReviewCreatedEvent value) {
        System.out.println("MovieReviewEventListener.listenCreated: " + key + " value= " + value);
    }

    @KafkaHandler
    public void listenUpdated(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, MovieReviewUpdatedEvent value) {
        System.out.println("MovieReviewEventListener.listenUpdated: " + key + " value= " + value);
    }

    @KafkaHandler
    public void listenDeleted(@Header(KafkaHeaders.RECEIVED_KEY) MovieReviewKey key, MovieReviewDeletedEvent value) {
        System.out.println("MovieReviewEventListener.listenDeleted: " + key + " value= " + value);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Unkown type received: " + object);
    }
}
