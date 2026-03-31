package me.mzalietin.mdbproject.moviereview.application;

import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;

public interface MovieReviewUseCases {

    void create(MovieReview review) throws ResourceAlreadyExistsException;;

    void update(MovieReview review) throws ResourceNotFoundException;;

    void delete(MovieReviewKey reviewKey) throws ResourceNotFoundException;;

    void deleteAllForUser(String username);
}
