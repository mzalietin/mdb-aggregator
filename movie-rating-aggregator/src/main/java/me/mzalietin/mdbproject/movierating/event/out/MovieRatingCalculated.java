package me.mzalietin.mdbproject.movierating.event.out;

import java.math.BigDecimal;

public record MovieRatingCalculated(BigDecimal averageRating, Integer reviewsCount) {
}
