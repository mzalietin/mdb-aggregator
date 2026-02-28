package me.mzalietin.imdbproject.movie.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public final class DomainTestFixtures {

    public static final String MOVIE_ID = "e693f305-bc96-4d63-871c-1dbb8d41dd51";
    public static final String MOVIE_NAME = "Forrest Gump";
    public static final LocalDate RELEASE_DATE = LocalDate.of(1994, Month.JUNE, 23);
    public static final BigDecimal MOVIE_RATING_ZERO = BigDecimal.ZERO;
    public static final BigDecimal MOVIE_RATING_NINE = BigDecimal.valueOf(9);
    public static final Integer ZERO_REVIEWS = 0;
    public static final Integer HUNDRED_REVIEWS = 100;

    public static Movie initialMovie() {
        return new Movie(MOVIE_ID, MOVIE_NAME, RELEASE_DATE, MOVIE_RATING_ZERO, ZERO_REVIEWS);
    }

    public static Movie testMovie() {
        return new Movie(MOVIE_ID, MOVIE_NAME, RELEASE_DATE, MOVIE_RATING_NINE, HUNDRED_REVIEWS);
    }
}
