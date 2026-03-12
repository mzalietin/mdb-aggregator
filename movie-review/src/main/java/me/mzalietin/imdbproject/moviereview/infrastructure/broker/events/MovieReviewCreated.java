package me.mzalietin.imdbproject.moviereview.infrastructure.broker.events;

public record MovieReviewCreated(
    Integer rating,
    String comment
) {
}
