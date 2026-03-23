package me.mzalietin.mdbproject.movie.application;

import java.time.LocalDate;
import java.util.Optional;
import me.mzalietin.mdbproject.movie.domain.model.Movie;

public interface MovieUseCases {

    Optional<Movie> findByName(String name);

    String create(String name, LocalDate releaseDate);
}
