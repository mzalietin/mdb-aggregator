package me.mzalietin.imdbproject.movierating.events.in;

public interface MovieRatingImpact {

    Integer absoluteRatingImpact();

    Integer reviewsCountImpact();
}
