package me.mzalietin.imdbproject.movie.core.usecase.ports;

import java.util.Collection;
import me.mzalietin.imdbproject.movie.core.domain.Movie;
import me.mzalietin.imdbproject.movie.core.domain.MovieNotFoundException;
import me.mzalietin.imdbproject.movie.core.domain.Ratings;

public interface MovieUseCases {

    String create(Movie movie);

    Double findRating(String movieName) throws MovieNotFoundException;

    Collection<Movie> getTopRated(Integer count);

    Collection<Movie> getTopRatedByUser(Integer count, String username);

    void updateMovies(Ratings ratings);
}
