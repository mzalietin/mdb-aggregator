package me.mzalietin.imdbproject.movie.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public final class DomainTestFixtures {

    public static final String MOVIE_ID = "e693f305-bc96-4d63-871c-1dbb8d41dd51";
    public static final String MOVIE_NAME = "Forrest Gump";
    public static final LocalDate RELEASE_DATE = LocalDate.of(1994, Month.JUNE, 23);
    public static final BigDecimal INITIAL_MOVIE_RATING = BigDecimal.ZERO;
    public static final BigDecimal MOVIE_RATING = BigDecimal.valueOf(680, 2);
    public static final Integer REVIEW_RATING_TEN = 10;
    public static final String REVIEW_COMMENT = "some comment";
    public static final String USERNAME = "jdoe";
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final Integer USER_AGE = 25;

//    public static User testUser() {
//        return new User(USERNAME, FIRST_NAME, LAST_NAME, USER_AGE);
//    }

    public static Movie initialTestMovie() {
        return new Movie(MOVIE_ID, MOVIE_NAME, RELEASE_DATE, INITIAL_MOVIE_RATING);
    }

    public static Movie testMovie() {
        return new Movie(MOVIE_ID, MOVIE_NAME, RELEASE_DATE, MOVIE_RATING);
    }

    //TODO remove?
//    public static MovieReview testMovieReview() {
//        return new MovieReview(USERNAME, MOVIE_ID, REVIEW_RATING_TEN, REVIEW_COMMENT);
//    }
//
//    public static MovieReviews testMovieReviews() {
//         return new MovieReviews(List.of(
//             new MovieReview("user_0", MOVIE_ID, 6, "comment_0"),
//             new MovieReview("user_1", MOVIE_ID, 6, "comment_1"),
//             new MovieReview("user_2", MOVIE_ID, 7, "comment_2"),
//             new MovieReview("user_3", MOVIE_ID, 7, "comment_3"),
//             new MovieReview("user_4", MOVIE_ID, 8, "comment_4")));
//    }
}
