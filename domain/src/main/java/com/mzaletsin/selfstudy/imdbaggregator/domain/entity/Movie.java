package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

public class Movie { //TODO immutable entities
    private String id;
    private String name;
    private java.time.LocalDate issueDate;
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

    public java.time.LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(final java.time.LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }
}
