package me.mzalietin.imdbproject.movierating.event.out;

import java.math.BigDecimal;

public record MovieRatingUpdated(BigDecimal averageRating, Integer reviewsCount) {
}
