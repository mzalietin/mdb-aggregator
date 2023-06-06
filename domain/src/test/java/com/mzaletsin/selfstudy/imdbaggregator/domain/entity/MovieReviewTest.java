package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.MOVIE_ID;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.REVIEW_RATING_TEN;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.REVIEW_COMMENT;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.USERNAME;

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
