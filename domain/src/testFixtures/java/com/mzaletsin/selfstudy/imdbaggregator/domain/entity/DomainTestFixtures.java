package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public final class DomainTestFixtures {

    public static final String MOVIE_ID = "28df567kl678l";
    public static final String MOVIE_NAME = "Forrest Gump";
    public static final LocalDate RELEASE_DATE = LocalDate.of(1994, Month.JUNE, 23);
    public static final BigDecimal INITIAL_RATING = BigDecimal.ZERO;
    public static final Integer RATING_TEN = 10;
    public static final String REVIEW_COMMENT = "some comment";
    public static final String USERNAME = "jdoe";
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final Integer USER_AGE = 25;

    public static Movie defaultMovie() {
        return new Movie(MOVIE_ID, MOVIE_NAME, RELEASE_DATE, INITIAL_RATING);
    }
}
