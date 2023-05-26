package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.impl;

import static com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.MovieReview.fromDomain;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository.MovieReviewRepository;
import java.util.Collection;

public class MovieReviewDao implements MovieReviewDataAccess {
    private final MovieReviewRepository repo;

    public MovieReviewDao(final MovieReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(MovieReviews reviews) {
        Collection<MovieReview> persistenceReviews = fromDomain(reviews);
        repo.saveAllAndFlush(persistenceReviews);
    }

    @Override
    public Integer countByMovieId(String movieId) {
        return repo.countByMovieId(movieId);
    }
}
