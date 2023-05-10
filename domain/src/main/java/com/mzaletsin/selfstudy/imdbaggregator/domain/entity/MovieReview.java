package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class MovieReview {
    private final Integer userId;
    private final String movieId;
    @Min(1) @Max(10)
    private final Integer rating;
    @Size(max = 10_000)
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
