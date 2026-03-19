package me.mzalietin.imdbproject.movierating.event.in;

import jakarta.validation.constraints.NotNull;

public record MovieReviewKey(
    @NotNull String username,
    @NotNull String movieId) {
}
