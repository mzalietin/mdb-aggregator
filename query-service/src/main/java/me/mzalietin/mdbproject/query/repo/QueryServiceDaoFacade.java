package me.mzalietin.mdbproject.query.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryServiceDaoFacade {

    private final MovieDao movieDao;
    private final MovieReviewDao movieReviewDao;
    private final UserDao userDao;

    @Autowired
    public QueryServiceDaoFacade(
        final MovieDao movieDao,
        final MovieReviewDao movieReviewDao,
        final UserDao userDao
    ) {
        this.movieDao = movieDao;
        this.movieReviewDao = movieReviewDao;
        this.userDao = userDao;
    }

    public MovieDao movieDao() {
        return movieDao;
    }

    public MovieReviewDao movieReviewDao() {
        return movieReviewDao;
    }

    public UserDao userDao() {
        return userDao;
    }
}
