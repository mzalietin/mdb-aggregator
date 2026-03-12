package me.mzalietin.imdbproject.movierating.infrastructure.repo;

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


}
