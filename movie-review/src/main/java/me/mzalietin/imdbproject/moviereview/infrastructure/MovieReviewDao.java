package me.mzalietin.imdbproject.moviereview.infrastructure;

import static me.mzalietin.imdbproject.moviereview.infrastructure.MovieReviewEntity.fromDomain;

import java.util.Collection;
import me.mzalietin.imdbproject.moviereview.domain.MovieReviews;
import me.mzalietin.imdbproject.moviereview.usecase.ports.MovieReviewDataAccess;

public class MovieReviewDao implements MovieReviewDataAccess {
    private final MovieReviewRepository repo;

    public MovieReviewDao(final MovieReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(MovieReviews reviews) {
        Collection<MovieReviewEntity> persistenceReviews = fromDomain(reviews);
        repo.saveAllAndFlush(persistenceReviews);
    }

    @Override
    public Integer countByMovieId(String movieId) {
        return repo.countByMovieId(movieId);
    }
}
