package me.mzalietin.mdbproject.movie.infrastructure.repo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mzalietin.mdbproject.movie.domain.model.Movie;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_seq")
    @SequenceGenerator(
        name = "movie_id_seq",
        sequenceName = "movie_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "average_rating", precision = 4, scale = 2, nullable = false)
    private BigDecimal averageRating;

    @Column(name = "reviews_count", nullable = false)
    private Integer reviewsCount;

    @PrePersist
    public void prePersist() {
        if (averageRating == null) {
            averageRating = BigDecimal.ZERO;
        }
        if (reviewsCount == null) {
            reviewsCount = 0;
        }
    }

    public Movie toModel() {
        return new Movie(id, name, releaseDate, averageRating, reviewsCount);
    }
}
