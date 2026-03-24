package me.mzalietin.imdbproject.query.repo.entity;

import java.math.BigDecimal;

public class Movie {
    private String id;
    private String name;
    private BigDecimal averageRating;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(final BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", averageRating=" + averageRating +
            '}';
    }
}
