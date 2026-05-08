package me.mzalietin.mdbproject.movie.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Movie(Long id, String name, LocalDate releaseDate, BigDecimal rating, Integer reviewsCount) {
}
