package me.mzalietin.mdbproject.queryservice.domain.model;

import java.math.BigDecimal;

public record Movie(String id,
                    String name,
                    BigDecimal avgRating,
                    Integer reviewsCount
) {
}
