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
     */
    void update(MovieReview review);

    /**
     * Remove Movie Review.
     *
     * @param review review to delete
     */
    void delete(MovieReview review);

    /**
     * Remove Movie Reviews.
     *
     * @param reviews reviews to delete
     */
    void delete(Collection<MovieReview> reviews);

    MovieReview findForUpdate(String username, String movieId) throws ResourceNotFoundException;

    MovieReview findForUpdate(MovieReviewKey reviewKey) throws ResourceNotFoundException;

    Collection<MovieReview> findForUpdate(String username);
}
