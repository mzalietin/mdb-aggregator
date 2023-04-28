package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

public class MovieReview {
    private final Integer userId;
    private final String movieId;
    private final Integer rating;
    private final String comment;

    public MovieReview(Integer userId, String movieId, Integer rating, String comment) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getUserId() {
        return userId;
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
