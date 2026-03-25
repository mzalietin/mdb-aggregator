package me.mzalietin.mdbproject.query;

import me.mzalietin.mdbproject.query.repo.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryServiceFacade {

    private final MovieDao movieDao;

    @Autowired
    public QueryServiceFacade(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public MovieDao movieDao() {
        return movieDao;
    }
}
