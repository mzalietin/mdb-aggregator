package me.mzalietin.mdbproject.queryservice.domain.service.spi;

import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieRatingUpdated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewKey;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewUpdated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.UserCreated;

public interface WriteOperations {

    void createMovie(String id, MovieCreated event);

    void updateMovie(String id, MovieRatingUpdated event);

    void createReview(ReviewKey id, ReviewCreated event);

    void updateReview(ReviewKey id, ReviewUpdated event);

    void deleteReview(ReviewKey id);

    void createUser(String username, UserCreated event);

    void deleteUser(String username);
}
