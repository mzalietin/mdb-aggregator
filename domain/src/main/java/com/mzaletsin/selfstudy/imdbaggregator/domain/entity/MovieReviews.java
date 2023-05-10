package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;

public class MovieReviews {
    @Valid
    private final Collection<MovieReview> reviews;

    public MovieReviews(Collection<MovieReview> reviews) {
        this.reviews = reviews;
    }

    public List<String> getUniqueMovieIds() {
        return reviews.stream()
            .map(MovieReview::getMovieId)
            .distinct()
            .toList();
    }

    public List<MovieReview> getReviewsByMovie(String movieId) {
        return reviews.stream()
            .filter(r -> r.getMovieId().equals(movieId))
            .toList();
    }

    public boolean isEmpty() {
        return reviews.isEmpty();
    }
}
