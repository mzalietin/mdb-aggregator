package me.mzalietin.mdbproject.query.broker;

import me.mzalietin.mdbproject.query.QueryServiceFacade;
import me.mzalietin.mdbproject.query.broker.event.MovieCreated;
import me.mzalietin.mdbproject.query.broker.event.MovieRatingUpdated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@KafkaListener(
    id = "query-service-movie-group",
    topics = "${query.service.kafka.in.movie-events-topic}",
    batch = "false",
    clientIdPrefix = "QueryServiceMovieConsumer"
)
public class MovieEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieEventListener.class);

    @Autowired
    QueryServiceFacade queryServiceFacade;

    @KafkaHandler
    public Mono<?> listen(@Header(KafkaHeaders.RECEIVED_KEY) String movieId, @Payload MovieCreated event) {
        logger.info("Received event for movieId={} event={}", movieId, event);
        return queryServiceFacade.movieDao().save(movieId, event);
    }

    @KafkaHandler
    public Mono<?> listen(@Header(KafkaHeaders.RECEIVED_KEY) String movieId, @Payload MovieRatingUpdated event) {
        logger.info("Received event for movieId={} event={}", movieId, event);
        return queryServiceFacade.movieDao().save(movieId, event);
    }
}
