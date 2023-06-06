package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in.MovieReviewUseCases;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieReviewDataAccess;
import jakarta.validation.Validator;
import java.util.List;

public class MovieReviewUseCasesInteractor extends ValidatingInteractor implements MovieReviewUseCases {
    private final MovieDataAccess movieDataAccess;
    private final MovieReviewDataAccess movieReviewDataAccess;

    public MovieReviewUseCasesInteractor(final Validator validator,
                                  final MovieDataAccess movieDataAccess,
                                  final MovieReviewDataAccess movieReviewDataAccess) {
        super(validator);
        this.movieDataAccess = movieDataAccess;
        this.movieReviewDataAccess = movieReviewDataAccess;
    }

    @Override
    public void save(final MovieReviews reviews) {
        if (!reviews.isEmpty()) {
            validate(reviews);

            List<String> movieIds = reviews.getUniqueMovieIds();

            for (String movieId : movieIds) {
                List<MovieReview> movieReviews = reviews.getReviewsByMovie(movieId);

                Movie movie = movieDataAccess.getById(movieId);
                Integer reviewsCount = movieReviewDataAccess.countByMovieId(movieId);

                Movie updatedMovie = movie.applyReviews(reviewsCount, movieReviews);

                movieDataAccess.save(updatedMovie);
            }

            movieReviewDataAccess.save(reviews);
        }
    }
}
