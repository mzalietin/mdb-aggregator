package me.mzalietin.imdbproject.movierating.domain.service.spi;

import me.mzalietin.imdbproject.movierating.domain.model.Movie;

public interface MovieDataAccess {

    String save(Movie movie);

    Movie getById(String id);
}
