package me.mzalietin.imdbproject.moviereview.core.usecase.ports;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.core.domain.MovieReview;

public interface MovieReviewUseCases {

    void create(MovieReview review);

    void create(Collection<MovieReview> reviews);
}
