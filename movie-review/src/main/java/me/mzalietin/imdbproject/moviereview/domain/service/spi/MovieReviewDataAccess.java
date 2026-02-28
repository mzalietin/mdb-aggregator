package me.mzalietin.imdbproject.moviereview.domain.service.spi;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReview;

public interface MovieReviewDataAccess {

    void save(MovieReview review);

    void save(Collection<MovieReview> reviews);
}
