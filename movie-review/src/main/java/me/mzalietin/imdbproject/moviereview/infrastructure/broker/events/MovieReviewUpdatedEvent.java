package me.mzalietin.imdbproject.moviereview.infrastructure.broker.events;

public record MovieReviewUpdatedEvent(
    Integer oldRating,
    String oldComment,
    Integer newRating,
    String newComment
) {
}
