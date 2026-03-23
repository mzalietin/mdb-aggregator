package me.mzalietin.mdbproject.movie.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Movie(
    String id, String name, LocalDate releaseDate, BigDecimal rating, Integer reviewsCount
) {
    public Movie(me.mzalietin.mdbproject.movie.domain.model.Movie model) {
        this(model.id(),  model.name(), model.releaseDate(), model.rating(), model.reviewsCount());
    }
}
