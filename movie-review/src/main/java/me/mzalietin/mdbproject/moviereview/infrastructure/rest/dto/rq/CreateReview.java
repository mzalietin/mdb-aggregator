package me.mzalietin.mdbproject.moviereview.infrastructure.rest.dto.rq;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;

public record CreateReview(
    @NotNull String username,
    @NotNull Long movieId,
    @NotNull @Min(1) @Max(10) Integer rating,
    @NotNull @Size(max = 5000) String comment
) {
    public MovieReview toModel() {
        return new MovieReview(null, username, movieId, rating, comment);
    }
}
