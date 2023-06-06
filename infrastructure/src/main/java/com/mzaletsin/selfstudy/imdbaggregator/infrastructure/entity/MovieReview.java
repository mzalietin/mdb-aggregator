package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "imdb_movie_review")
@IdClass(MovieReviewId.class)
public class MovieReview {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Id
    @Column(name = "movie_id", nullable = false)
    private String movieId;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "comment", nullable = true)
    private String comment;

    public MovieReview() {
    }

    public MovieReview(final String username,
                       final String movieId,
                       final Integer rating,
                       final String comment) {
        this.username = username;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
    }

    public static Collection<MovieReview> fromDomain(MovieReviews movieReviews) {
        return movieReviews.getReviews().stream()
            .map(r -> new MovieReview(r.getUsername(), r.getMovieId(), r.getRating(), r.getComment()))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public String getUsername() {
        return username;
    }

    public String getMovieId() {
        return movieId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final MovieReview that = (MovieReview) o;
        return Objects.equals(username, that.username) && Objects.equals(movieId, that.movieId) && Objects.equals(
            rating, that.rating) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, movieId, rating, comment);
    }

    @Override
    public String toString() {
        return "MovieReview{" +
            "username='" + username + '\'' +
            ", movieId='" + movieId + '\'' +
            ", rating=" + rating +
            ", comment='" + comment + '\'' +
            '}';
    }
}
