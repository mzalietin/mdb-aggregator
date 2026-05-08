package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReview;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "movie_review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewEntity {
    @Id
    private Long id;
    private String username;
    private String movieId;
    private Integer rating;
    private String comment;

    public MovieReviewEntity(Long id, MovieReview movieReview) {
        this.id = id;
        this.username = movieReview.username();
        this.movieId = movieReview.movieId();
        this.rating = movieReview.rating();
        this.comment = movieReview.comment();
    }

    public MovieReview toModel() {
        return new MovieReview(username, movieId, rating, comment);
    }
}
