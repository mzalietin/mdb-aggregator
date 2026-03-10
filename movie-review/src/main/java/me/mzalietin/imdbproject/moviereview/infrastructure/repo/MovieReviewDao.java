package me.mzalietin.imdbproject.moviereview.infrastructure.repo;

import java.util.Collection;
import java.util.stream.Collectors;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewDao implements MovieReviewDataAccess {
    private final MovieReviewRepository repo;

    @Autowired
    public MovieReviewDao(final MovieReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(final MovieReview r) {
        repo.save(new MovieReviewEntity(r.username(), r.movieId(), r.rating(), r.comment()));
    }

    @Override
    public void save(final Collection<MovieReview> reviews) {
        var persistenceReviews = reviews
            .stream()
            .map(r -> new MovieReviewEntity(r.username(), r.movieId(), r.rating(), r.comment()))
            .collect(Collectors.toList());
        repo.saveAll(persistenceReviews);
    }
}
