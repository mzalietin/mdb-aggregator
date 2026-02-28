package me.mzalietin.imdbproject.movie.domain.model;


import static me.mzalietin.imdbproject.movie.domain.model.DomainTestFixtures.initialMovie;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MovieTest {

    @ParameterizedTest
    @MethodSource("ratings")
    void givenMovie_whenUpdateRating_thenReturnsInstanceWithUpdatedRating(
        int[] newRatings,
        BigDecimal expectedRating,
        Integer expectedReviewsCount)
    {
        var movie = initialMovie();

        var result = movie.updateRating(newRatings);

        Assertions.assertEquals(expectedRating, result.rating());
        Assertions.assertEquals(expectedReviewsCount, result.reviewsCount());
    }

    private static Stream<Arguments> ratings() {
        return Stream.of(
            Arguments.of(new int[] {10}, BigDecimal.valueOf(1000, 2), 1),
            Arguments.of(new int[] {6, 6, 7, 7, 8}, BigDecimal.valueOf(680, 2), 5)
        );
    }
}
