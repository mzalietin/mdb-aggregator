package me.mzalietin.mdbproject.moviereview.application;

import java.util.Collection;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.EventStore;
import me.mzalietin.mdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieReviewUseCasesImpl implements MovieReviewUseCases {

    @Autowired
    MovieReviewDataAccess dataAccess;

    @Autowired
    EventStore eventStore;

    @Override
    @Transactional("transactionManager")
    public void create(final MovieReview review) {
        dataAccess.create(review);
        eventStore.sendCreated(review);
    }

    @Override
    @Transactional("transactionManager")
    public void update(final MovieReview review) {
        final MovieReview old = dataAccess.update(review);
        eventStore.sendUpdated(old, review);
    }

    @Override
    @Transactional("transactionManager")
    public void delete(final MovieReviewKey reviewKey) {
        final MovieReview deleted = dataAccess.delete(reviewKey);
        eventStore.sendDeleted(deleted);
    }

    @Override
    @Transactional("transactionManager")
    public void deleteAllForUser(final String username) {
        final Collection<MovieReview> removedReviews = dataAccess.deleteAllByUser(username);
        eventStore.sendDeleted(removedReviews);
    }
}
