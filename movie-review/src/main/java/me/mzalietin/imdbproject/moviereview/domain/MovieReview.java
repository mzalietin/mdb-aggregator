package me.mzalietin.imdbproject.moviereview.domain;

public class MovieReview {
    private final String username;
    private final String movieId;
    private final Integer rating;
    private final String comment;

    public MovieReview(String username, String movieId, Integer rating, String comment) {
        this.username = username;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public String getMovieId() {
        return movieId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
