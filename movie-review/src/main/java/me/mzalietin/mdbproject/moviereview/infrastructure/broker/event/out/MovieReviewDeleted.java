package me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.out;

public record MovieReviewDeleted(Integer rating, String comment) {}
