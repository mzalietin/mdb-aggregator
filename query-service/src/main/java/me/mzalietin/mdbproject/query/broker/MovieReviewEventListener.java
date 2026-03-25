package me.mzalietin.mdbproject.query.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.transaction.annotation.Transactional;

//@Component
public class MovieReviewEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieReviewEventListener.class);

    @Autowired
    //MovieDataAccess movieDataAccess;

    @KafkaListener(
        id = "query-service-group",
        topics = "${query.service.kafka.in.review-events-topic}",
        batch = "false",
        clientIdPrefix = "MovieRatingConsumer"
    )
    @Transactional("transactionManager")
    public void listen(
        @Header(KafkaHeaders.RECEIVED_KEY) String movieId,
        String event,
        Acknowledgment ack
    ) {
        logger.debug("Received event for movieId={} event={}", movieId, event);

        //movieDataAccess.updateRatingInfo(movieId, event.averageRating(), event.reviewsCount());
        ack.acknowledge();
    }
}
