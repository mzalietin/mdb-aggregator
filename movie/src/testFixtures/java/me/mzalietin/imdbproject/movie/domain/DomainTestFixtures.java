package me.mzalietin.imdbproject.movie.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public final class DomainTestFixtures {

    public static final String MOVIE_ID = "e693f305-bc96-4d63-871c-1dbb8d41dd51";
    public static final String MOVIE_NAME = "Forrest Gump";
    public static final LocalDate RELEASE_DATE = LocalDate.of(1994, Month.JUNE, 23);
    public static final BigDecimal INITIAL_MOVIE_RATING = BigDecimal.ZERO;
    public static final Integer INITIAL_REVIEWS_COUNT = 0;

    public static Movie initialTestMovie() {
        return new Movie(MOVIE_ID, MOVIE_NAME, RELEASE_DATE, INITIAL_MOVIE_RATING, INITIAL_REVIEWS_COUNT);
    }
}
