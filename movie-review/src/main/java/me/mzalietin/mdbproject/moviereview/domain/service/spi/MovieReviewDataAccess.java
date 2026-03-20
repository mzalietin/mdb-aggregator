package me.mzalietin.mdbproject.moviereview.domain.service.spi;

import java.util.Collection;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;

public interface MovieReviewDataAccess {

    /**
     * Create Review.
     *
     * @param review new data
     * @throws ResourceAlreadyExistsException if review already exists for given user and movie
     */
    void create(MovieReview review) throws ResourceAlreadyExistsException;

    /**
     * Update Movie Review.
     *
     * @param review updated (new) data
     * @return previous (old) data
     * @throws ResourceNotFoundException if review doesn't exist
     */
    MovieReview update(MovieReview review) throws ResourceNotFoundException;

    /**
     * Remove Movie Review.
     *
     * @param key review key
     * @return deleted data
     * @throws ResourceNotFoundException if review doesn't exist
     */
    MovieReview delete(MovieReviewKey key) throws ResourceNotFoundException;

    /**
     * Remove Reviews.
     *
     * @param username user name
     * @return deleted data
     */
    Collection<MovieReview> deleteAllByUser(String username);
}
