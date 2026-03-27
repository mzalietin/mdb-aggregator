package me.mzalietin.mdbproject.movie.domain.service.spi;

import java.math.BigDecimal;
import java.time.LocalDate;
import me.mzalietin.mdbproject.movie.domain.model.Movie;
import me.mzalietin.mdbproject.movie.domain.model.ResourceNotFoundException;

public interface MovieDataAccess {

    Movie createMovie(String name, LocalDate releaseDate);

    void updateRatingInfo(String movieId, BigDecimal rating, Integer reviewsCount) throws ResourceNotFoundException;
}
