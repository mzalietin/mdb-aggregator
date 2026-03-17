package me.mzalietin.imdbproject.movie.infrastructure.repo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mdb_movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "rating", precision = 4, scale = 2, nullable = false)
    private BigDecimal rating;

    @Column(name = "accumulated_real_rating", nullable = false)
    private Integer accumulatedRealRating;

    @Column(name = "reviews_count", nullable = false)
    private Integer reviewsCount;

    @PrePersist
    public void prePersist() {
        if (rating == null) {
            rating = BigDecimal.ZERO;
        }
        if (accumulatedRealRating == null) {
            accumulatedRealRating = 0;
        }
        if (reviewsCount == null) {
            reviewsCount = 0;
        }
    }
}
