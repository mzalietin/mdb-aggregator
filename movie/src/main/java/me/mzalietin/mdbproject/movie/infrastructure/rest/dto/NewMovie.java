package me.mzalietin.mdbproject.movie.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record NewMovie(
    @NotNull String name,
    @NotNull LocalDate releaseDate) {
}
