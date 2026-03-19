package me.mzalietin.mdbproject.movierating.event.out;

import java.math.BigDecimal;

public record MovieRatingUpdated(BigDecimal averageRating, Integer reviewsCount) {
}
