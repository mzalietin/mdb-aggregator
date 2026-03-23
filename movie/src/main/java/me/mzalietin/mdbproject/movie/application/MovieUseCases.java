package me.mzalietin.mdbproject.movie.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import me.mzalietin.mdbproject.movie.domain.model.Movie;

public interface MovieUseCases {

    Optional<Movie> findByName(String name);

    List<Movie> findTopMovies(Integer limit);

    String create(String name, LocalDate releaseDate);
}
