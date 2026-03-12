package me.mzalietin.imdbproject.moviereview.infrastructure.broker.events;

public record MovieReviewUpdated(
    Integer oldRating,
    String oldComment,
    Integer newRating,
    String newComment
) {
}
