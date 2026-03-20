package me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.out;

public record MovieReviewCreated(Integer rating, String comment) {}
