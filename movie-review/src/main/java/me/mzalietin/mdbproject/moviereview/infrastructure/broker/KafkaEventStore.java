package me.mzalietin.mdbproject.moviereview.infrastructure.broker;

import java.util.Collection;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.EventStore;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.out.MovieReviewCreated;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.out.MovieReviewDeleted;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.out.MovieReviewUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventStore implements EventStore {

    @Autowired
    KafkaTemplate<String, Object> kt;

    @Value("${movie.review.context.kafka.out.events-topic}")
    String eventsTopic;

    @Override
    public void sendCreated(String id, final MovieReview r) {
        kt.send(eventsTopic, id, new MovieReviewCreated(r.username(), r.movieId(), r.rating(), r.comment()));
    }

    @Override
    public void sendUpdated(final MovieReview old, final MovieReview newRev) {
        kt.send(eventsTopic, old.id(), new MovieReviewUpdated(old.rating(), old.comment(), newRev.rating(), newRev.comment()));
    }

    @Override
    public void sendDeleted(final MovieReview r) {
        kt.send(eventsTopic, r.id(), new MovieReviewDeleted(r.rating(), r.comment()));
    }

    @Override
    public void sendDeleted(final Collection<MovieReview> deletedReviews) {
        deletedReviews.forEach(
            r -> kt.send(eventsTopic, r.id(), new MovieReviewDeleted(r.rating(), r.comment()))
        );
    }
}
