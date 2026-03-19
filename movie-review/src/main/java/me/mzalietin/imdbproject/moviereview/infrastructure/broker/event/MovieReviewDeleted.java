package me.mzalietin.imdbproject.moviereview.infrastructure.broker.event;

public record MovieReviewDeleted(
    Integer rating,
    String comment
) {
}
