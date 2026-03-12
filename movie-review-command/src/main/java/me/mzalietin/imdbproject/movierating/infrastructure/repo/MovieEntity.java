package me.mzalietin.imdbproject.movierating.infrastructure.repo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mzalietin.imdbproject.movierating.domain.model.Movie;

@Entity
@Table(name = "imdb_movie")
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

    @Column(name = "reviews_count")
    private Integer reviewsCount;

    public static MovieEntity fromDomain(Movie domainMovie) {
        return new MovieEntity(
            domainMovie.id(),
            domainMovie.name(),
            domainMovie.releaseDate(),
            domainMovie.rating(),
            domainMovie.reviewsCount()
        );
    }

    public static Movie toDomain(MovieEntity persistenceMovieEntity) {
        return new Movie(
            persistenceMovieEntity.id,
            persistenceMovieEntity.name,
            persistenceMovieEntity.releaseDate,
            persistenceMovieEntity.rating,
            persistenceMovieEntity.reviewsCount
        );
    }
}
