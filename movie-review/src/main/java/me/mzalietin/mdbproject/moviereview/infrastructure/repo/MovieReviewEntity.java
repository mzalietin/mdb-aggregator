package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;

@Entity
@Table(name = "mdb_movie_review")
@IdClass(MovieReviewKey.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewEntity {
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

    public MovieReviewEntity(MovieReview movieReview) {
        this.username = movieReview.username();
        this.movieId = movieReview.movieId();
        this.rating = movieReview.rating();
        this.comment = movieReview.comment();
    }

    public MovieReview toModel() {
        return new MovieReview(username, movieId, rating, comment);
    }
}
