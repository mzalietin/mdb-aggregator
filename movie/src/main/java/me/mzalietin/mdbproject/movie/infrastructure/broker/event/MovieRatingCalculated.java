package me.mzalietin.mdbproject.movie.infrastructure.broker.event;

import java.math.BigDecimal;

public record MovieRatingCalculated(BigDecimal averageRating, Integer reviewsCount) {

}
