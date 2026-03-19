package me.mzalietin.mdbproject.moviereview.domain.model;

public record MovieReview(String username, String movieId, Integer rating, String comment) {
}
