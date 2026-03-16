package me.mzalietin.imdbproject.movierating.domain.model.events;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record MovieReviewCreated(
    @NotNull @Min(1) @Max(10) Integer rating,
    @NotNull @Length(max = 5000) String comment
) implements RatingImpactEvent {

    @Override
    public Integer get() {
        return rating;
    }
}
