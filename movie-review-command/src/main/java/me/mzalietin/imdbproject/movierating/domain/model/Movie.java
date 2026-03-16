package me.mzalietin.imdbproject.movierating.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Movie(String id, String name, LocalDate releaseDate, BigDecimal rating, Integer accumulatedRealRating, Integer reviewsCount) {
}
