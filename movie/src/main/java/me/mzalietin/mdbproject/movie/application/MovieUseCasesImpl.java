package me.mzalietin.mdbproject.movie.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import me.mzalietin.mdbproject.movie.domain.model.Movie;
import me.mzalietin.mdbproject.movie.domain.service.spi.EventStore;
import me.mzalietin.mdbproject.movie.domain.service.spi.MovieDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieUseCasesImpl implements MovieUseCases {

    @Autowired
    MovieDataAccess movieDataAccess;

    @Autowired
    EventStore eventStore;

    @Override
    public Optional<Movie> findByName(final String name) {
        return movieDataAccess.findMovie(name);
    }

    @Override
    public List<Movie> findTopMovies(Integer limit) {
        return movieDataAccess.findTopMoviesByRating(limit);
    }

    @Override
    public List<Movie> findByIds(final List<String> movieIds) {
        return movieDataAccess.findByIds(movieIds);
    }

    @Override
    @Transactional("transactionManager")
    public String create(final String name, final LocalDate releaseDate) {
        var newMovie = movieDataAccess.createMovie(name, releaseDate);
        eventStore.sendCreated(newMovie);
        return newMovie.id();
    }

    @Override
    @Transactional("transactionManager")
    public void updateRating(String id, BigDecimal newRating, Integer newReviewsCount) {
        eventStore.sendRatingUpdated(id, newRating, newReviewsCount);
        movieDataAccess.updateRatingInfo(id, newRating, newReviewsCount);
    }
}
