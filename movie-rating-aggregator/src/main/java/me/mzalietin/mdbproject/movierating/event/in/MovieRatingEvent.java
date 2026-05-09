package me.mzalietin.mdbproject.movierating.event.in;

public interface MovieRatingEvent {

    Long movieId();

    Integer absoluteRatingImpact();

    Integer reviewsCountImpact();
}
