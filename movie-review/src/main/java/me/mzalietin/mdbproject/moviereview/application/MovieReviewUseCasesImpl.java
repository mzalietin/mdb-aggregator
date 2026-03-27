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
        eventStore.sendCreated(review);
        dataAccess.create(review);
    }

    @Override
    @Transactional("transactionManager")
    public void update(final MovieReview newReview) {
        final MovieReview oldReview = dataAccess.findForUpdate(newReview.username(), newReview.movieId());
        eventStore.sendUpdated(oldReview, newReview);
        dataAccess.update(newReview);
    }

    @Override
    @Transactional("transactionManager")
    public void delete(final MovieReviewKey reviewKey) {
        final MovieReview review = dataAccess.findForUpdate(reviewKey);
        eventStore.sendDeleted(review);
        dataAccess.delete(review);
    }

    @Override
    @Transactional("transactionManager")
    public void deleteAllForUser(final String username) {
        final Collection<MovieReview> forRemoval = dataAccess.findForUpdate(username);
        eventStore.sendDeleted(forRemoval);
        dataAccess.delete(forRemoval);
    }
}
