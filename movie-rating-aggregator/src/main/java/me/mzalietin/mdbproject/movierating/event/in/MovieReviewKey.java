package me.mzalietin.mdbproject.movierating.event.in;

import jakarta.validation.constraints.NotNull;

public record MovieReviewKey(
    @NotNull String username,
    @NotNull String movieId) {
}
