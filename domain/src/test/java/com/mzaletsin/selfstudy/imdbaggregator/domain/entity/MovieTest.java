package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.INITIAL_MOVIE_RATING;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.MOVIE_ID;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.MOVIE_NAME;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.RELEASE_DATE;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.initialTestMovie;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.testMovieReview;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.testMovieReviews;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MovieTest {

    @Test
    void givenMovie_whenGetValue_thenReturnsValue() {
        var movie = new Movie(MOVIE_ID, MOVIE_NAME, RELEASE_DATE, INITIAL_MOVIE_RATING);

        Assertions.assertEquals(MOVIE_ID, movie.getId());
        Assertions.assertEquals(MOVIE_NAME, movie.getName());
        Assertions.assertEquals(RELEASE_DATE, movie.getReleaseDate());
        Assertions.assertEquals(INITIAL_MOVIE_RATING, movie.getRating());
    }

    @ParameterizedTest
    @MethodSource("reviews")
    void givenMovie_whenApplyReviews_thenReturnsInstanceWithUpdatedRating(Collection<MovieReview> newReviews,
                                                                          BigDecimal expectedRating) {
        var initialReviewsCount = 0;

        var movie = initialTestMovie();

        var result = movie.applyReviews(initialReviewsCount, newReviews);

        Assertions.assertEquals(expectedRating, result.getRating());
    }

    private static Stream<Arguments> reviews() {
        return Stream.of(
            Arguments.of(List.of(), BigDecimal.ZERO),
            Arguments.of(List.of(
                testMovieReview()),
                BigDecimal.valueOf(1000, 2)), //10
            Arguments.of(
                testMovieReviews().getReviews(),
                BigDecimal.valueOf(680, 2)) //6.8
        );
    }
}
