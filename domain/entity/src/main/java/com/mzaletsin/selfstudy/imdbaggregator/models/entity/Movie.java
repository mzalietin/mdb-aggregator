package com.mzaletsin.selfstudy.imdbaggregator.models.entity;

import java.time.LocalDate;

public class Movie {
    private String id;
    private String name;
    private LocalDate issueDate;
    private Double rating;

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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(final LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }
}
