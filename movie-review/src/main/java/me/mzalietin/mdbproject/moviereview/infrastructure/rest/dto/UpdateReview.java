package me.mzalietin.mdbproject.moviereview.infrastructure.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateReview(
    @NotNull @Min(1) @Max(10) Integer rating,
    @NotNull @Size(max = 5000) String comment
) {
}
