package me.mzalietin.imdbproject.movie.usecase.ports;

import java.util.Collection;
import me.mzalietin.imdbproject.movie.domain.Movie;
import me.mzalietin.imdbproject.movie.domain.MovieNotFoundException;
import me.mzalietin.imdbproject.movie.domain.Ratings;

public interface MovieUseCases {

    String create(Movie movie);

    Double findRating(String movieName) throws MovieNotFoundException;

    Collection<Movie> getTopRated(Integer count);

    Collection<Movie> getTopRatedByUser(Integer count, String username);

    void updateMovies(Ratings ratings);
}
