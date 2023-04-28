package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import java.time.LocalDate;

public class Movie {
    private final String id;
    private final String name;
    private final LocalDate releaseDate;
    private final Double rating;

    public Movie(String id, String name, LocalDate releaseDate, Double rating) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
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

    public Double getRating() {
        return rating;
    }
}
