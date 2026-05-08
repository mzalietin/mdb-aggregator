package me.mzalietin.mdbproject.moviereview.application;

import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;

public interface MovieReviewUseCases {

    String create(MovieReview review) throws ResourceAlreadyExistsException;

    void update(String id, Integer newRating, String newComment) throws ResourceNotFoundException;

    void delete(String id) throws ResourceNotFoundException;;

    void deleteAllForUser(String username);
}
