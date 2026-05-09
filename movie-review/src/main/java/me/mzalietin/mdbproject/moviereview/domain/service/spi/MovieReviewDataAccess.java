package me.mzalietin.mdbproject.moviereview.domain.service.spi;

import java.util.Collection;
import java.util.Map;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;

public interface MovieReviewDataAccess {

    /**
     * Create Review.
     *
     * @param review new data
     * @throws ResourceAlreadyExistsException if review already exists for given user and movie
     */
    String create(MovieReview review) throws ResourceAlreadyExistsException;

    /**
     * Update Movie Review.
     *
     * @param review updated (new) data
     */
    void update(MovieReview review);

    /**
     * Remove Movie Review.
     *
     * @param id review ID
     */
    void delete(String id);

    void delete(Collection<String> ids);

    MovieReview findByIdIfExists(String id) throws ResourceNotFoundException;

    Map<String, MovieReview> findByUser(String username);
}
