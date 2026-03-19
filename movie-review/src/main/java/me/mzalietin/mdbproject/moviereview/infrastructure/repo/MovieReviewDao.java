package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewDao implements MovieReviewDataAccess {
    private final MovieReviewRepository repo;

    @Autowired
    public MovieReviewDao(final MovieReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public void create(final MovieReview review) throws ResourceAlreadyExistsException {
        var key = new MovieReviewKey(review.username(), review.movieId());
        if (repo.existsById(key)){
            throw new ResourceAlreadyExistsException("Review Key = " + key);
        } else {
            repo.save(new MovieReviewEntity(review.username(), review.movieId(), review.rating(), review.comment()));
        }
    }

    @Override
    public void update(final MovieReview review) throws ResourceNotFoundException {
        var key = new MovieReviewKey(review.username(), review.movieId());
        if (repo.existsById(key)){
            repo.save(new MovieReviewEntity(review.username(), review.movieId(), review.rating(), review.comment()));
        } else {
            throw new ResourceNotFoundException("Review Key = " + key);
        }
    }

    @Override
    public void delete(final MovieReviewKey key) throws ResourceNotFoundException {
        if (repo.existsById(key)){
            repo.deleteById(key);
        } else {
            throw new ResourceNotFoundException("Review Key = " + key);
        }
    }
}
