package me.mzalietin.mdbproject.movie.infrastructure.repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import me.mzalietin.mdbproject.movie.domain.model.Movie;
import me.mzalietin.mdbproject.movie.domain.model.ResourceNotFoundException;
import me.mzalietin.mdbproject.movie.domain.service.spi.MovieDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDao implements MovieDataAccess {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Optional<Movie> findMovie(final String movieName) {
        return movieRepository.findByName(movieName).map(MovieEntity::toModel);
    }

    @Override
    public String createMovie(final String name, final LocalDate releaseDate) {
        final MovieEntity saved = movieRepository.save(new MovieEntity(null, name, releaseDate, null, null));
        return saved.getId();
    }

    @Override
    public void updateRatingInfo(final String movieId, final BigDecimal rating, final Integer reviewsCount) {
        final int updatedRows = movieRepository.updateRating(movieId, rating, reviewsCount);
        if (updatedRows == 0) {
            throw new ResourceNotFoundException("Movie not found by id: " + movieId);
        }
    }
}
