package me.mzalietin.imdbproject.movie.infrastructure.repo;

import java.util.Collection;
import me.mzalietin.imdbproject.movie.domain.model.Movie;
import me.mzalietin.imdbproject.movie.domain.model.MovieNotFoundException;
import me.mzalietin.imdbproject.movie.domain.service.spi.MovieDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDao implements MovieDataAccess {
    private final MovieRepository repo;

    @Autowired
    public MovieDao(MovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public String save(Movie movie) {
        var persistenceEntity = repo.saveAndFlush(MovieEntity.fromDomain(movie));
        return persistenceEntity.getId();
    }

    @Override
    public Movie getByName(String name) {
        var persistenceEntity = repo.findByName(name)
            .orElseThrow(() -> MovieNotFoundException.byName(name));
        return MovieEntity.toDomain(persistenceEntity);
    }

    @Override
    public Movie getById(String id) {
        var persistenceEntity = repo.findById(id)
            .orElseThrow(() -> MovieNotFoundException.byId(id));
        return MovieEntity.toDomain(persistenceEntity);
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
