package me.mzalietin.imdbproject.movierating.events.out;

import java.math.BigDecimal;

public record MovieRatingUpdated(BigDecimal averageRating, Integer reviewsCount) {
}
