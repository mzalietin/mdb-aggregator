package me.mzalietin.mdbproject.moviereview.application;

import java.util.Map;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
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
    public String create(final MovieReview review) {
        //eventStore.sendCreated(review);
        return dataAccess.create(review);
    }

    @Override
    @Transactional("transactionManager")
    public void update(String id, Integer newRating, String newComment) {
        final MovieReview oldReview = dataAccess.findByIdIfExists(id);
        final MovieReview newReview = new MovieReview(oldReview.username(), oldReview.movieId(), newRating, newComment);
        //eventStore.sendUpdated(oldReview, newReview);
        dataAccess.update(id, newReview);
    }

    @Override
    @Transactional("transactionManager")
    public void delete(final String id) {
        MovieReview review = dataAccess.findByIdIfExists(id);
        //eventStore.sendDeleted(review);
        dataAccess.delete(id);
    }

    @Override
    @Transactional("transactionManager")
    public void deleteAllForUser(final String username) {
        final Map<String, MovieReview> forRemoval = dataAccess.findByUser(username);
        if (!forRemoval.isEmpty()) {
            //eventStore.sendDeleted(forRemoval);
            dataAccess.delete(forRemoval.keySet());
        }
    }
}
