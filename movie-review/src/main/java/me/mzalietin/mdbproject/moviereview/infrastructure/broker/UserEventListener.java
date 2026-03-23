package me.mzalietin.mdbproject.moviereview.infrastructure.broker;

import me.mzalietin.mdbproject.moviereview.application.MovieReviewUseCases;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.in.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @Autowired
    MovieReviewUseCases movieReviewUseCases;

    @KafkaListener(
        id = "movie-review-user-context-group",
        topics = "${movie.review.context.kafka.in.user-events-topic}",
        batch = "false",
        clientIdPrefix = "UserEventsConsumer"
    )
    public void onDeleted(
        @Header(KafkaHeaders.RECEIVED_KEY) String username,
        @Payload(required = false) UserEvent value,
        Acknowledgment ack
    ) {
        if (value == null) {
            logger.info("Received user deleted event username={}", username); //todo populate & log kafka correlation id
            movieReviewUseCases.deleteAllForUser(username);
        }
        ack.acknowledge();
    }
}
