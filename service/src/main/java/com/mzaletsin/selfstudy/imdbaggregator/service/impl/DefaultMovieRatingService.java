package com.mzaletsin.selfstudy.imdbaggregator.service.impl;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.MovieDao;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.MovieReviewDao;
import com.mzaletsin.selfstudy.imdbaggregator.models.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.models.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.service.MovieRatingService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultMovieRatingService implements MovieRatingService {
    private MovieReviewDao movieReviewDao;
    private MovieDao movieDao;

    @Override
    public void saveAndRecalculate(final Collection<MovieReview> reviews) {
        Map<String, List<MovieReview>> movieIdToReviewsMap = new HashMap<>();

        List<String> moviesToUpdate = reviews.stream()
            .map(MovieReview::getMovieId)
            .distinct()
            .toList();

        moviesToUpdate.forEach(id -> movieIdToReviewsMap.put(id, new ArrayList<>()));

        reviews.forEach(r -> movieIdToReviewsMap.get(r.getMovieId()).add(r));

        for (Map.Entry<String, List<MovieReview>> idToReviews: movieIdToReviewsMap.entrySet()) {
            String movieId = idToReviews.getKey();
            List<MovieReview> movieReviews = idToReviews.getValue();

            Movie movie = movieDao.getById(movieId);

            // movie.applyReviews(currentReviewsCount, newReviews) ??
            Double currentRating = movie.getRating();
            Integer reviewsCount = movieReviewDao.countByMovieId(movieId);
            Double currentVolume = currentRating * reviewsCount;

            Integer newReviewsVolume = movieReviews.stream()
                .mapToInt(MovieReview::getRating)
                .sum();

            Double newVolume = currentVolume + newReviewsVolume;
            Integer newReviewsCount = reviewsCount + movieReviews.size();
            Double newRating = newVolume / newReviewsCount;

            movie.setRating(newRating);
            //
            movieDao.save(movie);
        }

        //movieReviewDao.save(reviews); TODO
    }

    @Override
    public Collection<Movie> getTopRated(final Integer positions) {
        return null;
    }

    @Override
    public Collection<Movie> getTopRatedByUser(final Integer positions,
        final String username) {

        return null;
    }
}
