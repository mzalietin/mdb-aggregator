package me.mzalietin.imdbproject.movierating.events.out;

import java.math.BigDecimal;

public record MovieRatingUpdated(Integer absoluteRating, Integer reviewsCount, BigDecimal averageRating) {
}
