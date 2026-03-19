package me.mzalietin.mdbproject.movie.infrastructure.broker.event;

import java.math.BigDecimal;

public record MovieRatingUpdated(BigDecimal averageRating, Integer reviewsCount) {

}
