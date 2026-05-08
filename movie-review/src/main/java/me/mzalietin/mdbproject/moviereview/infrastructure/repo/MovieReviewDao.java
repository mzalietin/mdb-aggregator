package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewDao implements MovieReviewDataAccess {
    private final JdbcAggregateOperations jdbcOperations;

    @Autowired
    public MovieReviewDao(final JdbcAggregateOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public String create(final MovieReview review) throws ResourceAlreadyExistsException {
        try {
            var saved = jdbcOperations.insert(new MovieReviewEntity(null, review));
            return saved.getId();
        } catch (DuplicateKeyException e) {
            throw new ResourceAlreadyExistsException("Review already exists for given user and movie", e);
        }
    }

    @Override
    public void update(final String id, final MovieReview updated) throws ResourceNotFoundException {
        jdbcOperations.update(new MovieReviewEntity(id, updated));
    }

    @Override
    public void delete(final String id) throws ResourceNotFoundException {
        jdbcOperations.deleteById(id, MovieReviewEntity.class);
    }

    @Override
    public void delete(final Collection<String> ids) {
        jdbcOperations.deleteAllById(ids, MovieReviewEntity.class);
    }

    @Override
    public MovieReview findByIdIfExists(final String id) throws ResourceNotFoundException {
        var entity = jdbcOperations.findById(id, MovieReviewEntity.class);
        if (entity == null) {
            throw new ResourceNotFoundException("Review Key = " + id);
        } else {
            return entity.toModel();
        }
    }

    @Override
    public Map<String, MovieReview> findByUser(final String username) {
        //todo address SQL injection
        return jdbcOperations.findAll(query(where("username").is(username)), MovieReviewEntity.class)
            .stream()
            .collect(Collectors.toMap(MovieReviewEntity::getId, MovieReviewEntity::toModel));
    }
}
