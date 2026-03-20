package me.mzalietin.mdbproject.moviereview.infrastructure.broker;

import me.mzalietin.mdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.in.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @Autowired
    MovieReviewDataAccess movieReviewDataAccess;

    @KafkaListener(
        id = "movie-review-user-context-group",
        topics = "${movie.review.context.kafka.user-events-topic}",
        batch = "false",
        clientIdPrefix = "UserEventsConsumer",
        containerFactory = "userListenerContainerFactory"
    )
    public void onDeleted(@Header(KafkaHeaders.RECEIVED_KEY) String username, @Payload(required = false) UserEvent value) {
        if (value == null) {
            logger.debug("Received user deleted event"); //todo log kafka correlation id
            movieReviewDataAccess.deleteAllByUser(username);
        }
    }
}
