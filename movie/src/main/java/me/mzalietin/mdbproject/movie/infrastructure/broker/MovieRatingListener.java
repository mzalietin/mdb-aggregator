package me.mzalietin.mdbproject.movie.infrastructure.broker;

import me.mzalietin.mdbproject.movie.application.MovieUseCases;
import me.mzalietin.mdbproject.movie.infrastructure.broker.event.MovieRatingCalculated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MovieRatingListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingListener.class);

    @Autowired
    MovieUseCases movieUseCases;

    @KafkaListener(
        id = "movie-context-group",
        topics = "movie-rating-output",
        batch = "false",
        clientIdPrefix = "MovieRatingConsumer"
    )
    @Transactional("transactionManager")
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) Long movieId, MovieRatingCalculated event, Acknowledgment ack) {
        logger.debug("Received event for movieId={} event={}", movieId, event);

        movieUseCases.updateRating(movieId, event.averageRating(), event.reviewsCount());
        ack.acknowledge();

        //todo propagate & log KafkaHeaders.CORRELATION_ID
    }
}
