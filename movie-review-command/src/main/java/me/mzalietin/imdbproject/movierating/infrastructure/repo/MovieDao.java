package me.mzalietin.imdbproject.movierating.infrastructure.repo;

import me.mzalietin.imdbproject.movierating.domain.model.Movie;
import me.mzalietin.imdbproject.movierating.domain.service.spi.MovieDataAccess;
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
        var persistenceEntity = repo.save(MovieEntity.fromDomain(movie));
        return persistenceEntity.getId();
    }

    @Override
    public Movie getById(String id) {
        var persistenceEntity = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        return MovieEntity.toDomain(persistenceEntity);
    }
}
