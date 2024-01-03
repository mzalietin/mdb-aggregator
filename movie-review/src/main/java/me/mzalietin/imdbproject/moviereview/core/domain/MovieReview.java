package me.mzalietin.imdbproject.moviereview.core.domain;

public record MovieReview(String username, String movieId, Integer rating, String comment) {
}
