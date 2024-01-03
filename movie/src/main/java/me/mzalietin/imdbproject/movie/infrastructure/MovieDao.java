package me.mzalietin.imdbproject.movie.infrastructure;

import static me.mzalietin.imdbproject.movie.infrastructure.MovieEntity.fromDomain;
import static me.mzalietin.imdbproject.movie.infrastructure.MovieEntity.toDomain;

import java.util.Collection;
import me.mzalietin.imdbproject.movie.domain.Movie;
import me.mzalietin.imdbproject.movie.domain.MovieNotFoundException;
import me.mzalietin.imdbproject.movie.usecase.ports.MovieDataAccess;

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
        var persistenceEntity = repo.findByName(name)
            .orElseThrow(() -> MovieNotFoundException.byName(name));
        return toDomain(persistenceEntity);
    }

    @Override
    public Movie getById(String id) {
        var persistenceEntity = repo.findById(id)
            .orElseThrow(() -> MovieNotFoundException.byId(id));
        return toDomain(persistenceEntity);
    }

    @Override
    public Collection<Movie> getTopRated(Integer limit) {
        //TODO implement
        return null;
    }

    @Override
    public Collection<Movie> getTopRated(Integer limit, String username) {
        //TODO implement
        return null;
    }
}
