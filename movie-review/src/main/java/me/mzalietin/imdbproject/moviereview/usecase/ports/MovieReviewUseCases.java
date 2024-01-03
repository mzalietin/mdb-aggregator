package me.mzalietin.imdbproject.moviereview.usecase.ports;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.domain.MovieReview;

public interface MovieReviewUseCases {

    void save(Collection<MovieReview> reviews);
}
