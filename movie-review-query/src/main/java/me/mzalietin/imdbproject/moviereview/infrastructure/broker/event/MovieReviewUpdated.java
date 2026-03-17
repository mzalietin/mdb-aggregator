package me.mzalietin.imdbproject.moviereview.infrastructure.broker.event;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public record MovieReviewUpdated(
    @Min(1) @Max(10) Integer oldRating,
    @Length(max = 5000) String oldComment,
    @Min(1) @Max(10) Integer newRating,
    @Length(max = 5000) String newComment
) {
}
