package me.mzalietin.mdbproject.movie.infrastructure.broker.event.out;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovieCreated(String name, LocalDate releaseDate, BigDecimal rating, Integer reviewsCount) {
}
