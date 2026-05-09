package me.mzalietin.mdbproject.moviereview.domain.service.spi;

import java.util.Collection;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;

public interface EventStore {

    void sendCreated(String id, MovieReview newReview);

    void sendUpdated(MovieReview old, MovieReview updatedReview);

    void sendDeleted(MovieReview deletedReview);

    void sendDeleted(Collection<MovieReview> deletedReviews);
}
