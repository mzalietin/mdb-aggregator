package me.mzalietin.mdbproject.moviereview.domain.model;

public final class DomainTestFixtures {
    public static final String REVIEW_ID = "e693f305-bc96-4d63-871c-1dbb8d41dd51";
    public static final Integer REVIEW_RATING_TEN = 10;
    public static final Long MOVIE_ID = 12345L;
    public static final String REVIEW_COMMENT = "some comment";
    public static final String USERNAME = "jdoe";

    public static MovieReview testMovieReview() {
        return new MovieReview(REVIEW_ID, USERNAME, MOVIE_ID, REVIEW_RATING_TEN, REVIEW_COMMENT);
    }
}
