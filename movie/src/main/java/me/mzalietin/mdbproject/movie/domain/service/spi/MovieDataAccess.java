package me.mzalietin.mdbproject.movie.domain.service.spi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import me.mzalietin.mdbproject.movie.domain.model.Movie;
import me.mzalietin.mdbproject.movie.domain.model.ResourceNotFoundException;

public interface MovieDataAccess {

    Optional<Movie> findMovie(String movieName);

    List<Movie> findTopMoviesByRating(Integer limit);

    List<Movie> findByIds(List<String> movieIds);

    Movie createMovie(String name, LocalDate releaseDate);

    void updateRatingInfo(String movieId, BigDecimal rating, Integer reviewsCount) throws ResourceNotFoundException;
}
