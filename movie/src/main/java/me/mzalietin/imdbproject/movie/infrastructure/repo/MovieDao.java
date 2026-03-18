package me.mzalietin.imdbproject.movie.infrastructure.repo;

import java.math.BigDecimal;
import me.mzalietin.imdbproject.movie.domain.model.ResourceNotFoundException;
import me.mzalietin.imdbproject.movie.domain.service.spi.MovieDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDao implements MovieDataAccess {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public void updateRatingInfo(final String movieId, final BigDecimal rating, final Integer reviewsCount) {
        final int updatedRows = movieRepository.updateRating(movieId, rating, reviewsCount);
        if (updatedRows == 0) {
            throw new ResourceNotFoundException("Movie not found by id: " + movieId);
        }
    }
}
