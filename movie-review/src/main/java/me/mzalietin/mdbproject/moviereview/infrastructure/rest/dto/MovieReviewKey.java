package me.mzalietin.mdbproject.moviereview.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;

public record MovieReviewKey(
    @NotNull String username,
    @NotNull String movieId
) {

    public me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey toModel() {
        return new me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey(username, movieId);
    }
}
