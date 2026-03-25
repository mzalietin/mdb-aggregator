package me.mzalietin.mdbproject.movie.infrastructure.broker.event.out;

import java.math.BigDecimal;

public record MovieRatingUpdated(BigDecimal averageRating, Integer reviewsCount) {
}
