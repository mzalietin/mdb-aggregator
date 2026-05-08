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
    void create(MovieReview review) throws ResourceAlreadyExistsException;

    /**
     * Update Movie Review.
     *
     * @param id review ID
     * @param review updated (new) data
     */
    void update(Long id, MovieReview review) throws ResourceNotFoundException;

    /**
     * Remove Movie Review.
     *
     * @param id review ID
     */
    void delete(Long id) throws ResourceNotFoundException;

    void delete(Collection<Long> ids);

    MovieReview findByIdIfExists(Long id) throws ResourceNotFoundException;

    Map<Long, MovieReview> findByUser(String username);
}
