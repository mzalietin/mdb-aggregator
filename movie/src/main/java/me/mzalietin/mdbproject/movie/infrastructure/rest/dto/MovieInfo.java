package me.mzalietin.mdbproject.movie.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import me.mzalietin.mdbproject.movie.domain.model.Movie;

public record MovieInfo(
    String id, String name, LocalDate releaseDate, BigDecimal rating, Integer reviewsCount
) {
    public MovieInfo(Movie model) {
        this(model.id(),  model.name(), model.releaseDate(), model.rating(), model.reviewsCount());
    }
}
