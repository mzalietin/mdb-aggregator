package me.mzalietin.mdbproject.moviereview.infrastructure.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record MovieReview(
    @NotNull String username,
    @NotNull String movieId,
    @NotNull @Min(1) @Max(10) Integer rating,
    @NotNull @Length(max = 5000) String comment
) {
    public me.mzalietin.mdbproject.moviereview.domain.model.MovieReview toModel() {
        return new me.mzalietin.mdbproject.moviereview.domain.model.MovieReview(username, movieId, rating, comment);
    }
}
