package me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.out;

public record MovieReviewCreated(String username,
                                 Long movieId,
                                 Integer rating,
                                 String comment
) {}
