package me.mzalietin.mdbproject.moviereview.domain.service.spi;

import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;

public interface MovieReviewDataAccess {

    void create(MovieReview review) throws ResourceAlreadyExistsException;

    void update(MovieReview review) throws ResourceNotFoundException;

    void delete(MovieReviewKey key) throws ResourceNotFoundException;

    void deleteAllByUser(String username);
}
