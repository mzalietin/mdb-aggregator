package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.SaveReviews;

import java.util.Collection;
import java.util.List;

final class SaveReviewsImplementation implements SaveReviews {
    private final MovieDataAccess movieDataAccess;
    private final MovieReviewDataAccess movieReviewDataAccess;

    public SaveReviewsImplementation(MovieDataAccess movieDataAccess,
                                     MovieReviewDataAccess movieReviewDataAccess) {
        this.movieDataAccess = movieDataAccess;
        this.movieReviewDataAccess = movieReviewDataAccess;
    }

    @Override
    public void save(Collection<MovieReview> reviews) {
        if (!reviews.isEmpty()) {
            List<String> movieIds = reviews.stream()
                .map(MovieReview::getMovieId)
                .distinct()
                .toList();

            for (String movieId : movieIds) {
                List<MovieReview> movieReviews = reviews.stream()
                    .filter(r -> r.getMovieId().equals(movieId))
                    .toList();

                Movie movie = movieDataAccess.getById(movieId);
                Integer reviewsCount = movieReviewDataAccess.countByMovieId(movieId);

                Movie updatedMovie = movie.applyReviews(reviewsCount, movieReviews);

                movieDataAccess.save(updatedMovie);
            }

            movieReviewDataAccess.save(reviews);
        }
    }
}
