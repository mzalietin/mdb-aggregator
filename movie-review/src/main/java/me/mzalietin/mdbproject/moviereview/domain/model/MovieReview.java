package me.mzalietin.mdbproject.moviereview.domain.model;

public record MovieReview(String id,
                          String username,
                          Long movieId,
                          Integer rating,
                          String comment
) {
}
