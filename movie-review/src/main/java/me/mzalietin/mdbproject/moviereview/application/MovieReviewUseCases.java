package me.mzalietin.mdbproject.moviereview.application;

import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;

public interface MovieReviewUseCases {

    void create(MovieReview review);

    void update(MovieReview review);

    void delete(MovieReviewKey reviewKey);
}
