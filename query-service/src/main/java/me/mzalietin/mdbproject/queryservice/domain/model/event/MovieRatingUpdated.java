package me.mzalietin.mdbproject.queryservice.domain.model.event;

import java.math.BigDecimal;

public record MovieRatingUpdated(BigDecimal averageRating, Integer reviewsCount) {

}
