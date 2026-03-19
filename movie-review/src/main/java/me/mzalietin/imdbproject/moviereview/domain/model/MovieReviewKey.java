package me.mzalietin.imdbproject.moviereview.domain.model;

import jakarta.validation.constraints.NotNull;

public record MovieReviewKey(@NotNull String username, @NotNull String movieId) {
}
