package me.mzalietin.imdbproject.moviereview.domain.service.spi;

import me.mzalietin.imdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;

public interface MovieReviewDataAccess {

    void save(MovieReview review);

    void delete(MovieReviewKey key);
}
