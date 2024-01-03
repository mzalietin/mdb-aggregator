package me.mzalietin.imdbproject.moviereview.core.usecase;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.core.domain.MovieReview;
import me.mzalietin.imdbproject.moviereview.core.usecase.ports.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.core.usecase.ports.MovieReviewUseCases;

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
