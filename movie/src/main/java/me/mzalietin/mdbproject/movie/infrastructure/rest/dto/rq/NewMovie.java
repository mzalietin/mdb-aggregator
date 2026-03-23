package me.mzalietin.mdbproject.movie.infrastructure.rest.dto.rq;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record NewMovie(
    @NotEmpty String name,
    @NotNull LocalDate releaseDate) {
}
