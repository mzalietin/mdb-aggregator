package me.mzalietin.imdbproject.moviereview.domain.service;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewUseCases;

public final class MovieReviewUseCasesInteractor implements MovieReviewUseCases {
    private final MovieReviewDataAccess movieReviewDataAccess;

    public MovieReviewUseCasesInteractor(final MovieReviewDataAccess movieReviewDataAccess) {
        this.movieReviewDataAccess = movieReviewDataAccess;
    }

    @Override
    public void create(final MovieReview review) {
        movieReviewDataAccess.save(review);
    }

    @Override
    public void create(final Collection<MovieReview> reviews) {
        movieReviewDataAccess.save(reviews);
    }
}
