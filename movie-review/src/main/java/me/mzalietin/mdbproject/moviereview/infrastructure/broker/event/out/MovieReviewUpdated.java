package me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.out;

public record MovieReviewUpdated(Integer oldRating, String oldComment, Integer newRating, String newComment) {}
