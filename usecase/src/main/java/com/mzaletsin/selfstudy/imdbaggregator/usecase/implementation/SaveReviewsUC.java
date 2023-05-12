package com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.SaveReviews;
import jakarta.validation.Validator;

import java.util.List;

public final class SaveReviewsUC extends BaseValidatingUseCase implements SaveReviews {
    private final MovieDataAccess movieDataAccess;
    private final MovieReviewDataAccess movieReviewDataAccess;

    public SaveReviewsUC(Validator validator,
                         MovieDataAccess movieDataAccess,
                         MovieReviewDataAccess movieReviewDataAccess) {
        super(validator);
        this.movieDataAccess = movieDataAccess;
        this.movieReviewDataAccess = movieReviewDataAccess;
    }

    @Override
    public void save(MovieReviews reviews) {
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
