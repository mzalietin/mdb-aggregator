package me.mzalietin.imdbproject.movierating.event.in;

public interface MovieRatingEvent {

    Integer absoluteRatingImpact();

    Integer reviewsCountImpact();
}
