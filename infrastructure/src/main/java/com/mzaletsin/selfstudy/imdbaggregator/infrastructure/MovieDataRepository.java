package com.mzaletsin.selfstudy.imdbaggregator.infrastructure;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository.MovieRepository;

import java.util.Collection;

public class MovieDataRepository implements MovieDataAccess {
    private final MovieRepository repo;

    public MovieDataRepository(MovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public String save(Movie movie) {
        return null;
    }

    @Override
    public Movie getByName(String name) {
        return null;
    }

    @Override
    public Movie getById(String id) {
        return null;
    }

    @Override
    public Collection<Movie> getTopRated(Integer count) {
        return null;
    }

    @Override
    public Collection<Movie> getTopRated(Integer count, String username) {
        return null;
    }
}
