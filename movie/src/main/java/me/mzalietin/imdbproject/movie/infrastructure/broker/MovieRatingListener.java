package me.mzalietin.imdbproject.movie.infrastructure.broker;

import me.mzalietin.imdbproject.movie.infrastructure.broker.event.MovieRatingUpdated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MovieRatingListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingListener.class);

    @KafkaListener(
        id = "movie-context-group",
        topics = "movie-rating-output",
        batch = "false",
        clientIdPrefix = "MovieRatingConsumer"
    )
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) String movieId, MovieRatingUpdated updateEvent) {

        logger.info("Received event for movieId={} event={}", movieId, updateEvent);
    }
}
