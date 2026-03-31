package me.mzalietin.mdbproject.movie.infrastructure.repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import me.mzalietin.mdbproject.movie.domain.model.Movie;
import me.mzalietin.mdbproject.movie.domain.service.spi.MovieDataAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDao implements MovieDataAccess {

    private static final Logger logger = LoggerFactory.getLogger(MovieDao.class);

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie createMovie(final String name, final LocalDate releaseDate) {
        final MovieEntity saved = movieRepository.save(new MovieEntity(null, name, releaseDate, null, null));
        return saved.toModel();
    }

    @Override
    public void updateRatingInfo(final String movieId, final BigDecimal rating, final Integer reviewsCount) {
        final int updatedRows = movieRepository.updateRating(movieId, rating, reviewsCount);
        if (updatedRows == 0) {
            logger.warn("Rating update failed for movieId {}, please make sure the movie exists", movieId);
        }
    }
}
