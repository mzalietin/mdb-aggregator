package me.mzalietin.imdbproject.moviereview.usecase;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.domain.MovieReview;
import me.mzalietin.imdbproject.moviereview.usecase.ports.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.usecase.ports.MovieReviewUseCases;

public class MovieReviewUseCasesInteractor implements MovieReviewUseCases {
    private final MovieReviewDataAccess movieReviewDataAccess;

    public MovieReviewUseCasesInteractor(final MovieReviewDataAccess movieReviewDataAccess) {
        this.movieReviewDataAccess = movieReviewDataAccess;
    }

    @Override
    public void save(final Collection<MovieReview> reviews) {
        movieReviewDataAccess.save(reviews);
    }
}
