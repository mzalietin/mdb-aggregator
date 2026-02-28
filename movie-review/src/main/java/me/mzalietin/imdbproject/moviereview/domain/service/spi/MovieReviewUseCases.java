package me.mzalietin.imdbproject.moviereview.domain.service.spi;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReview;

public interface MovieReviewUseCases {

    void create(MovieReview review);

    void create(Collection<MovieReview> reviews);
}
