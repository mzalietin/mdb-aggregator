package me.mzalietin.mdbproject.queryservice.domain.service.spi;

import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieRatingUpdated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewUpdated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.UserCreated;

public interface WriteOperations {

    void createMovie(Long id, MovieCreated event);

    void updateMovie(Long id, MovieRatingUpdated event);

    void createReview(String id, ReviewCreated event);

    void updateReview(String id, ReviewUpdated event);

    void deleteReview(String id);

    void createUser(String username, UserCreated event);

    void deleteUser(String username);
}
