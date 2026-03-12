package me.mzalietin.imdbproject.movie.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.stream.IntStream;

public record Movie(String id, String name, LocalDate releaseDate, BigDecimal rating, Integer reviewsCount) {
}
