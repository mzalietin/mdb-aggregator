package me.mzalietin.imdbproject.moviereview.core.usecase.ports;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.core.domain.MovieReview;

public interface MovieReviewDataAccess {

    void save(MovieReview review);

    void save(Collection<MovieReview> reviews);
}
