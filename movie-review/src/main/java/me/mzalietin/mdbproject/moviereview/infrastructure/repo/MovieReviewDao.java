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
    public void create(final MovieReview review) throws ResourceAlreadyExistsException {
        jdbcOperations.insert(new MovieReviewEntity(null, review));//todo catch DuplicateKeyException
    }

    @Override
    public void update(final Long id, final MovieReview updated) throws ResourceNotFoundException {
        jdbcOperations.update(new MovieReviewEntity(id, updated));
    }

    @Override
    public void delete(final Long id) throws ResourceNotFoundException {
        jdbcOperations.deleteById(id, MovieReviewEntity.class);
    }

    @Override
    public void delete(final Collection<Long> ids) {
        jdbcOperations.deleteAllById(ids, MovieReviewEntity.class);
    }

    @Override
    public MovieReview findByIdIfExists(final Long id) throws ResourceNotFoundException {
        var entity = jdbcOperations.findById(id, MovieReviewEntity.class);
        if (entity == null) {
            throw new ResourceNotFoundException("Review Key = " + id);
        } else {
            return entity.toModel();
        }
    }

    @Override
    public Map<Long, MovieReview> findByUser(final String username) {
        //todo address SQL injection
        return jdbcOperations.findAll(query(where("username").is(username)), MovieReviewEntity.class)
            .stream()
            .collect(Collectors.toMap(MovieReviewEntity::getId, MovieReviewEntity::toModel));
    }
}
