package me.mzalietin.imdbproject.moviereview.usecase.ports;

import me.mzalietin.imdbproject.moviereview.domain.MovieReviews;

public interface MovieReviewUseCases {

    void save(MovieReviews reviews);
        //throws MovieNotFoundException;
}
