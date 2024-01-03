package me.mzalietin.imdbproject.moviereview.gateway.dataprovider;

import java.util.Collection;
import java.util.stream.Collectors;
import me.mzalietin.imdbproject.moviereview.core.domain.MovieReview;
import me.mzalietin.imdbproject.moviereview.core.usecase.ports.MovieReviewDataAccess;

public class MovieReviewDao implements MovieReviewDataAccess {
    private final MovieReviewRepository repo;

    public MovieReviewDao(final MovieReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Collection<MovieReview> reviews) {
        var persistenceReviews = reviews
            .stream()
            .map(r -> new MovieReviewEntity(r.username(), r.movieId(), r.rating(), r.comment()))
            .collect(Collectors.toList());
        repo.saveAllAndFlush(persistenceReviews);
    }
}
