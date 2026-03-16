package me.mzalietin.imdbproject.moviereview.infrastructure.broker.events;

public record MovieReviewDeleted(
    Integer rating,
    String comment
) {
}
