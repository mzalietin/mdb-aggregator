package me.mzalietin.mdbproject.movierating.event.in;

import jakarta.validation.constraints.NotNull;

public record MovieReviewDeleted(
    @NotNull Long movieId,
    @NotNull Integer rating,
    @NotNull String comment
) implements MovieRatingEvent {

    @Override
    public Integer absoluteRatingImpact() {
        return - rating;
    }

    @Override
    public Integer reviewsCountImpact() {
        return -1;
    }
}
