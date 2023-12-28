package me.mzalietin.imdbproject.moviereview.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static me.mzalietin.imdbproject.moviereview.domain.DomainTestFixtures.*;

class MovieReviewTest {

    @Test
    void givenMovieReview_whenGetValue_thenReturnsValue() {
        var movieReview = new MovieReview(USERNAME, MOVIE_ID, REVIEW_RATING_TEN, REVIEW_COMMENT);

        Assertions.assertEquals(USERNAME, movieReview.getUsername());
        Assertions.assertEquals(MOVIE_ID, movieReview.getMovieId());
        Assertions.assertEquals(REVIEW_RATING_TEN, movieReview.getRating());
        Assertions.assertEquals(REVIEW_COMMENT, movieReview.getComment());
    }
}
