package me.mzalietin.mdbproject.queryservice.domain.model;

public class MovieReview {
    private String username;
    private String movieId;
    private String movieName;
    private Integer rating;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(final String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(final Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
            "username='" + username + '\'' +
            ", movieId='" + movieId + '\'' +
            ", movieName='" + movieName + '\'' +
            ", rating=" + rating +
            '}';
    }
}
