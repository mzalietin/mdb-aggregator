package me.mzalietin.imdbproject.movie.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import me.mzalietin.imdbproject.movie.domain.Movie;

@Entity
@Table(name = "imdb_movie")
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

    public MovieEntity() {
    }

    public MovieEntity(final String name,
                 final LocalDate releaseDate,
                 final BigDecimal rating) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    private MovieEntity(final String id,
                 final String name,
                 final LocalDate releaseDate,
                 final BigDecimal rating) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public static MovieEntity fromDomain(Movie domainMovie) {
        return new MovieEntity(
            domainMovie.getId(),
            domainMovie.getName(),
            domainMovie.getReleaseDate(),
            domainMovie.getRating()
        );
    }

    public static Movie toDomain(MovieEntity persistenceMovieEntity) {
        return new Movie(
            persistenceMovieEntity.getId(),
            persistenceMovieEntity.getName(),
            persistenceMovieEntity.getReleaseDate(),
            persistenceMovieEntity.getRating()
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public BigDecimal getRating() {
        return rating;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final MovieEntity movieEntity = (MovieEntity) o;
        return Objects.equals(id, movieEntity.id) && Objects.equals(name, movieEntity.name) && Objects.equals(releaseDate,
            movieEntity.releaseDate) && Objects.equals(rating, movieEntity.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, rating);
    }
}
