package me.mzalietin.mdbproject.moviereview.infrastructure.broker;

import java.util.Collection;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.EventStore;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
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
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserEventListener {

    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @Autowired
    MovieReviewDataAccess movieReviewDataAccess;

    @Autowired
    EventStore eventStore;

    @KafkaListener(
        id = "movie-review-user-context-group",
        topics = "${movie.review.context.kafka.in.user-events-topic}",
        batch = "false",
        clientIdPrefix = "UserEventsConsumer"
    )
    @Transactional("transactionManager")
    public void onDeleted(
        @Header(KafkaHeaders.RECEIVED_KEY) String username,
        @Payload(required = false) UserEvent value,
        Acknowledgment ack
    ) {
        if (value == null) {
            logger.debug("Received user deleted event"); //todo populate & log kafka correlation id
            final Collection<MovieReview> removedReviews = movieReviewDataAccess.deleteAllByUser(username);
            removedReviews.forEach(review -> {
                eventStore.sendDeleted(review);
            });
        }
        ack.acknowledge();
    }
}
