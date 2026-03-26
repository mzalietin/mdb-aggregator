package me.mzalietin.mdbproject.queryservice.infrastructure.repo;

import java.math.BigDecimal;
import me.mzalietin.mdbproject.queryservice.domain.model.Movie;
import me.mzalietin.mdbproject.queryservice.domain.model.User;
import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieRatingUpdated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewKey;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewUpdated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.UserCreated;
import me.mzalietin.mdbproject.queryservice.domain.service.spi.ReadOperations;
import me.mzalietin.mdbproject.queryservice.domain.service.spi.WriteOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class QueryServiceDaoFacade implements ReadOperations, WriteOperations {

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

    @Override
    public Mono<BigDecimal> movieRatingByName(final String name) {
        return movieDao.ratingByName(name);
    }

    @Override
    public Flux<Movie> topMovies(final Integer limit) {
        return movieDao.topByRating(limit);
    }

    @Override
    public Flux<Movie> topByUser(final String username, final Integer limit) {
        return null;
    }

    @Override
    public Mono<User> userInfo(final String username) {
        return userDao.getUserInfo(username);
    }

    @Override
    public void createMovie(final String id, final MovieCreated event) {
        movieDao.save(id, event);
    }

    @Override
    public void updateMovie(final String id, final MovieRatingUpdated event) {
        movieDao.save(id, event);
    }

    @Override
    public void createReview(final ReviewKey id, final ReviewCreated event) {
        movieReviewDao.save(id, event);
    }

    @Override
    public void updateReview(final ReviewKey id, final ReviewUpdated event) {
        movieReviewDao.save(id, event);
    }

    @Override
    public void deleteReview(final ReviewKey id) {
        movieReviewDao.delete(id);
    }

    @Override
    public void createUser(final String username, final UserCreated event) {
        userDao.save(username, event);
    }

    @Override
    public void deleteUser(final String username) {
        userDao.delete(username);
    }
}
