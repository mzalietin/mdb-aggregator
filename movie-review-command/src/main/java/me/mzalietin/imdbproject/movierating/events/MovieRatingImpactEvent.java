package me.mzalietin.imdbproject.movierating.events;

public interface MovieRatingImpactEvent {

    Integer absoluteRatingImpact();

    Integer reviewsCountImpact();
}
