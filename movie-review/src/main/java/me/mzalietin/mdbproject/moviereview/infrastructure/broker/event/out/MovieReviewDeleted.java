package me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.out;

import jakarta.validation.constraints.NotNull;

public record MovieReviewDeleted(
    @NotNull Integer rating,
    @NotNull String comment
) {
}
