package com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.SaveReviews;

import java.util.List;

final class SaveReviewsUC implements SaveReviews {
    private final MovieDataAccess movieDataAccess;
    private final MovieReviewDataAccess movieReviewDataAccess;

    SaveReviewsUC(MovieDataAccess movieDataAccess,
                  MovieReviewDataAccess movieReviewDataAccess) {
        this.movieDataAccess = movieDataAccess;
        this.movieReviewDataAccess = movieReviewDataAccess;
    }

    @Override
    public void save(MovieReviews reviews) {
        if (!reviews.isEmpty()) {
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
