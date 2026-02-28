package me.mzalietin.imdbproject.movie.domain.service.spi;

import java.util.Collection;
import me.mzalietin.imdbproject.movie.domain.model.Movie;
import me.mzalietin.imdbproject.movie.domain.model.MovieNotFoundException;
import me.mzalietin.imdbproject.movie.domain.model.Ratings;

public interface MovieUseCases {

    String create(Movie movie);

    Double findRating(String movieName) throws MovieNotFoundException;

    Collection<Movie> getTopRated(Integer count);

    Collection<Movie> getTopRatedByUser(Integer count, String username);

    void updateMovies(Ratings ratings);
}
