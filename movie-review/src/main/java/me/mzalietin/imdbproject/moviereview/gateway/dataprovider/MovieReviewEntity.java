package me.mzalietin.imdbproject.moviereview.gateway.dataprovider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imdb_movie_review")
@IdClass(MovieReviewId.class)
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
}
