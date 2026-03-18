package me.mzalietin.imdbproject.moviereview.domain.service.spi;

import me.mzalietin.imdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.imdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.imdbproject.moviereview.domain.model.ResourceNotFoundException;

public interface MovieReviewDataAccess {

    void create(MovieReview review) throws ResourceAlreadyExistsException;

    void update(MovieReview review) throws ResourceNotFoundException;

    void delete(MovieReviewKey key) throws ResourceNotFoundException;
}
