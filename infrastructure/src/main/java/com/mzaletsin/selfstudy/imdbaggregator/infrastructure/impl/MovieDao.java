package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.impl;

import static com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.Movie.fromDomain;
import static com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.Movie.toDomain;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.exception.MovieNotFoundException;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository.MovieRepository;
import java.util.Collection;

public class MovieDao implements MovieDataAccess {
    private final MovieRepository repo;

    public MovieDao(MovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public String save(Movie movie) {
        var persistenceEntity = repo.saveAndFlush(fromDomain(movie));
        return persistenceEntity.getId();
    }

    @Override
    public Movie getByName(String name) {
        var persistenceEntity = repo.findByName(name);
        return toDomain(persistenceEntity);
    }

    @Override
    public Movie getById(String id) {
        var persistenceEntity = repo.findById(id)
            .orElseThrow(() -> new MovieNotFoundException(id));
        return toDomain(persistenceEntity);
    }

    @Override
    public Collection<Movie> getTopRated(Integer count) {
        //TODO
        return null;
    }

    @Override
    public Collection<Movie> getTopRated(Integer count, String username) {
        //TODO
        return null;
    }
}
