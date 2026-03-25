package me.mzalietin.mdbproject.movie.infrastructure.broker;

import java.math.BigDecimal;
import me.mzalietin.mdbproject.movie.domain.model.Movie;
import me.mzalietin.mdbproject.movie.domain.service.spi.EventStore;
import me.mzalietin.mdbproject.movie.infrastructure.broker.event.out.MovieCreated;
import me.mzalietin.mdbproject.movie.infrastructure.broker.event.out.MovieRatingUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventStore implements EventStore {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${movie.context.kafka.out.events-topic}")
    String eventsOutputTopic;

    @Override
    public void sendCreated(final Movie movie) {
        kafkaTemplate.send(eventsOutputTopic, movie.id(), new MovieCreated(movie.name(), movie.releaseDate(), movie.rating(),
            movie.reviewsCount()));
    }

    @Override
    public void sendRatingUpdated(final String movieId, final BigDecimal newRating, final Integer newReviewsCount) {
        kafkaTemplate.send(eventsOutputTopic, movieId, new MovieRatingUpdated(newRating, newReviewsCount));
    }
}
