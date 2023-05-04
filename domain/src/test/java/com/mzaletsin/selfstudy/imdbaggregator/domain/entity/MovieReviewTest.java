package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.TestFixtures.MOVIE_ID;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.TestFixtures.RATING_TEN;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.TestFixtures.REVIEW_COMMENT;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.TestFixtures.USER_ID;

class MovieReviewTest {

    @Test
    void givenMovieReview_whenGetValue_thenReturnsValue() {
        var comment = "comment";

        var movieReview = new MovieReview(USER_ID, MOVIE_ID, RATING_TEN, REVIEW_COMMENT);

        Assertions.assertEquals(USER_ID, movieReview.getUserId());
        Assertions.assertEquals(MOVIE_ID, movieReview.getMovieId());
        Assertions.assertEquals(RATING_TEN, movieReview.getRating());
        Assertions.assertEquals(REVIEW_COMMENT, movieReview.getComment());
    }
}
