package com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;

import java.util.Collection;

public interface MovieDataAccess {

    String save(Movie movie);

    Movie getByName(String name);

    Movie getById(String id);

    Collection<Movie> getTopRated(Integer count);

    Collection<Movie> getTopRated(Integer count, String username);
}
