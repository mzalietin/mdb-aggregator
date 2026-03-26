package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import java.util.Collection;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
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
    public void update(final MovieReview updated) throws ResourceNotFoundException {
        var entity = new MovieReviewEntity(updated);
        repo.save(entity);
    }

    @Override
    public void delete(final MovieReview review) throws ResourceNotFoundException {
        var entity = new MovieReviewEntity(review);
        repo.delete(entity);
    }

    @Override
    public void delete(final Collection<MovieReview> reviews) {
        var entities = reviews.stream().map(MovieReviewEntity::new).toList();
        repo.deleteAll(entities);
    }

    @Override
    public MovieReview findForUpdate(final MovieReviewKey key) throws ResourceNotFoundException {
        return this.findForUpdate(key.username(), key.movieId());
    }

    @Override
    public MovieReview findForUpdate(final String username, final String movieId) throws ResourceNotFoundException {
        return repo.findByUsernameAndMovieIdForUpdate(username, movieId)
            .map(MovieReviewEntity::toModel)
            .orElseThrow(() -> new ResourceNotFoundException("Review Key = " + new MovieReviewKey(username, movieId)));
    }

    @Override
    public Collection<MovieReview> findForUpdate(final String username) {
        return repo.findByUsernameForUpdate(username)
            .stream()
            .map(MovieReviewEntity::toModel)
            .toList();
    }
}
