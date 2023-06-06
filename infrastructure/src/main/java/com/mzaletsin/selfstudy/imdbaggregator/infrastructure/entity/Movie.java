package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "imdb_movie")
public class Movie {
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

    public Movie() {
    }

    public Movie(final String name,
                 final LocalDate releaseDate,
                 final BigDecimal rating) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    private Movie(final String id,
                 final String name,
                 final LocalDate releaseDate,
                 final BigDecimal rating) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public static Movie fromDomain(com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie domainMovie) {
        return new Movie(
            domainMovie.getId(),
            domainMovie.getName(),
            domainMovie.getReleaseDate(),
            domainMovie.getRating()
        );
    }

    public static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie toDomain(Movie persistenceMovie) {
        return new com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie(
            persistenceMovie.getId(),
            persistenceMovie.getName(),
            persistenceMovie.getReleaseDate(),
            persistenceMovie.getRating()
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
        final Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(releaseDate,
            movie.releaseDate) && Objects.equals(rating, movie.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, rating);
    }
}
