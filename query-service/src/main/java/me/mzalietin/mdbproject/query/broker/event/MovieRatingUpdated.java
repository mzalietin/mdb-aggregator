package me.mzalietin.mdbproject.query.broker.event;

import java.math.BigDecimal;

public record MovieRatingUpdated(BigDecimal averageRating, Integer reviewsCount) {

}
