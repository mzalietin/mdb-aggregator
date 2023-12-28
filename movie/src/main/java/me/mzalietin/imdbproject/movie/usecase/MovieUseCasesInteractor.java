package me.mzalietin.imdbproject.movie.usecase;

import java.util.Collection;
import me.mzalietin.imdbproject.movie.domain.Movie;
import me.mzalietin.imdbproject.movie.usecase.ports.MovieDataAccess;
import me.mzalietin.imdbproject.movie.usecase.ports.MovieUseCases;

public final class MovieUseCasesInteractor implements MovieUseCases {
    private final MovieDataAccess movieDataAccess;

    public MovieUseCasesInteractor(final MovieDataAccess movieDataAccess) {
        this.movieDataAccess = movieDataAccess;
    }

    @Override
    public String create(final Movie movie) {
        return movieDataAccess.save(movie);
    }

    @Override
    public Double findRating(final String movieName) {
        var movie = movieDataAccess.getByName(movieName);
        return movie.getRating().doubleValue();
    }

    @Override
    public Collection<Movie> getTopRated(final Integer count) {
        return movieDataAccess.getTopRated(count);
    }

    @Override
    public Collection<Movie> getTopRatedByUser(final Integer count, final String username) {
        return movieDataAccess.getTopRated(count, username);
    }
}
