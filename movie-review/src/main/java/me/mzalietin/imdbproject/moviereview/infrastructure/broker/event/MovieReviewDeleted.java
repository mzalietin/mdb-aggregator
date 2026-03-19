package me.mzalietin.imdbproject.moviereview.infrastructure.broker.event;

import jakarta.validation.constraints.NotNull;

public record MovieReviewDeleted(
    @NotNull Integer rating,
    @NotNull String comment
) {
}
