package me.mzalietin.mdbproject.moviereview.domain.service.spi;

import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;

public interface EventStore {

    void sendCreated(MovieReview newReview);

    void sendUpdated(MovieReview old, MovieReview updatedReview);

    void sendDeleted(MovieReview deletedReview);
}
