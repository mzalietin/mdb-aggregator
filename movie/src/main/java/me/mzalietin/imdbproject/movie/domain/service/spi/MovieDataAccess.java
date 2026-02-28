package me.mzalietin.imdbproject.movie.domain.service.spi;

import java.util.Collection;
import me.mzalietin.imdbproject.movie.domain.model.Movie;

public interface MovieDataAccess {

    String save(Movie movie);

    Movie getByName(String name);

    Movie getById(String id);

    Collection<Movie> getTopRated(Integer limit);

    Collection<Movie> getTopRated(Integer limit, String username);
}
