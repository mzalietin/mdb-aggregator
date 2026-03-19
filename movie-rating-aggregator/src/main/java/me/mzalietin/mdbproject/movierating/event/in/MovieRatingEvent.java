package me.mzalietin.mdbproject.movierating.event.in;

public interface MovieRatingEvent {

    Integer absoluteRatingImpact();

    Integer reviewsCountImpact();
}
