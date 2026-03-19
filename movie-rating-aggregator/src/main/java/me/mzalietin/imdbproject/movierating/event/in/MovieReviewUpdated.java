package me.mzalietin.imdbproject.movierating.event.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record MovieReviewUpdated(
    @NotNull @Min(1) @Max(10) Integer oldRating,
    @NotNull @Length(max = 5000) String oldComment,
    @NotNull @Min(1) @Max(10) Integer newRating,
    @NotNull @Length(max = 5000) String newComment
) implements MovieRatingEvent {

    @Override
    public Integer absoluteRatingImpact() {
        return newRating - oldRating;
    }

    @Override
    public Integer reviewsCountImpact() {
        return 0;
    }
}
