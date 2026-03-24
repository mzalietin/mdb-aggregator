package me.mzalietin.mdbproject.moviereview.application;

import java.util.List;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;

public interface MovieReviewUseCases {

    void create(MovieReview review);

    void update(MovieReview review);

    void delete(MovieReviewKey reviewKey);

    void deleteAllForUser(String username);
    /**
     * Top favorite user movies by username.
     *
     * @param username user name
     * @param limit top list size
     * @return list of movie IDs
     */
    List<String> topByUser(String username, Integer limit);
}
