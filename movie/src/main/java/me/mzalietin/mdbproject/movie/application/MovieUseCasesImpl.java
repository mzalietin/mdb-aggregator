package me.mzalietin.mdbproject.movie.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import me.mzalietin.mdbproject.movie.domain.model.Movie;
import me.mzalietin.mdbproject.movie.domain.service.spi.MovieDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieUseCasesImpl implements MovieUseCases {

    @Autowired
    MovieDataAccess movieDataAccess;

    @Override
    public Optional<Movie> findByName(final String name) {
        return movieDataAccess.findMovie(name);
    }

    @Override
    public List<Movie> findTopMovies(Integer limit) {
        return movieDataAccess.findTopMoviesByRating(limit);
    }

    @Override
    @Transactional
    public String create(final String name, final LocalDate releaseDate) {
        return movieDataAccess.createMovie(name, releaseDate);
    }
}
