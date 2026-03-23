package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import java.util.Collection;
import java.util.List;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public MovieReview update(final MovieReview updated) throws ResourceNotFoundException {
        var key = new MovieReviewKey(updated.username(), updated.movieId());

        var entity = repo.findById(key).orElseThrow(() -> new ResourceNotFoundException("Review Key = " + key));

        var oldState = entity.toModel();

        entity.setRating(updated.rating());
        entity.setComment(updated.comment());

        return oldState;
    }

    @Override
    public MovieReview delete(final MovieReviewKey key) throws ResourceNotFoundException {
        var entity = repo.findById(key).orElseThrow(() -> new ResourceNotFoundException("Review Key = " + key));
        var deleted = entity.toModel();
        repo.delete(entity);
        return deleted;
    }

    @Override
    public Collection<MovieReview> deleteAllByUser(final String username) {
        var reviews = repo.findByUsername(username);
        var deleted = reviews.stream().map(MovieReviewEntity::toModel).toList();
        repo.deleteAll(reviews);
        return deleted;
    }

    @Override
    public List<String> topByUser(final String username, final Integer limit) {
        return repo.findAll(PageRequest.of(0, limit, Sort.by(Sort.Order.desc("rating"))))
            .getContent()
            .stream()
            .map(MovieReviewEntity::getMovieId)
            .toList();
    }
}
