package me.mzalietin.mdbproject.queryservice.domain.model.event;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovieCreated(String name, LocalDate releaseDate, BigDecimal rating, Integer reviewsCount) {
}
