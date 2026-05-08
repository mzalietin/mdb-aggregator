package me.mzalietin.mdbproject.moviereview.infrastructure.broker;

import java.util.Collection;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
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
    KafkaTemplate<MovieReviewKey, Object> kt;

    @Value("${movie.review.context.kafka.out.events-topic}")
    String eventsTopic;

    @Override
    public void sendCreated(final MovieReview r) {
        //todo
        //kt.send(eventsTopic, new MovieReviewKey(r.username(), r.movieId()), new MovieReviewCreated(r.rating(), r.comment()));
    }

    @Override
    public void sendUpdated(final MovieReview old, final MovieReview r) {
//        kt.send(eventsTopic, new MovieReviewKey(r.username(), r.movieId()),
//            new MovieReviewUpdated(old.rating(), old.comment(), r.rating(), r.comment()));
    }

    @Override
    public void sendDeleted(final MovieReview r) {
//        kt.send(eventsTopic, new MovieReviewKey(r.username(), r.movieId()),
//            new MovieReviewDeleted(r.rating(), r.comment()));
    }

    @Override
    public void sendDeleted(final Collection<MovieReview> deletedReviews) {
//        deletedReviews.forEach(r -> {
//            kt.send(eventsTopic, new MovieReviewKey(r.username(), r.movieId()),
//                new MovieReviewDeleted(r.rating(), r.comment()));
//        });
    }
}
