package me.mzalietin.imdbproject.moviereview.infrastructure.broker.event;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public record MovieReviewCreated(
    @Min(1) @Max(10) Integer rating,
    @Length(max = 5000) String comment
) {
}
