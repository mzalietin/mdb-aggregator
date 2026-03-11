package me.mzalietin.imdbproject.moviereview.infrastructure.broker.events;

public record MovieReviewCreatedEvent(
    Integer rating,
    String comment
) {
}
