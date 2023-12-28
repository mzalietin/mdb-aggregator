package me.mzalietin.imdbproject.moviereview.infrastructure;

import java.io.Serializable;
import java.util.Objects;

public class MovieReviewId implements Serializable {
    private String username;
    private String movieId;

    public MovieReviewId() {
    }

    public MovieReviewId(final String username, final String movieId) {
        this.username = username;
        this.movieId = movieId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final MovieReviewId that = (MovieReviewId) o;
        return Objects.equals(username, that.username) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, movieId);
    }
}
