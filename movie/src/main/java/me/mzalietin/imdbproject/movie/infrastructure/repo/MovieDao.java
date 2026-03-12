package me.mzalietin.imdbproject.movie.infrastructure.repo;

import java.util.Collection;
import me.mzalietin.imdbproject.movie.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDao {
    private final MovieRepository repo;

    @Autowired
    public MovieDao(MovieRepository repo) {
        this.repo = repo;
    }

    public Collection<Movie> getTopRated(Integer limit) {
        //TODO implement
        return null;
    }

    public Collection<Movie> getTopRated(Integer limit, String username) {
        //TODO implement
        return null;
    }
}
