package me.mzalietin.imdbproject.moviereview.usecase.ports;

import me.mzalietin.imdbproject.moviereview.domain.MovieReviews;

public interface MovieReviewDataAccess {

    void save(MovieReviews reviews);

    Integer countByMovieId(String movieId);
}
