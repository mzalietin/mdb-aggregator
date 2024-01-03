package me.mzalietin.imdbproject.moviereview.domain;

public record MovieReview(String username, String movieId, Integer rating, String comment) {
}
