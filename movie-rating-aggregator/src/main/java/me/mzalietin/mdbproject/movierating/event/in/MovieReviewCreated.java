package me.mzalietin.mdbproject.movierating.event.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MovieReviewCreated(
    @NotNull @Min(1) @Max(10) Integer rating,
    @NotNull @Size(max = 5000) String comment
) implements MovieRatingEvent {

    @Override
    public Integer absoluteRatingImpact() {
        return rating;
    }

    @Override
    public Integer reviewsCountImpact() {
        return 1;
    }
}
