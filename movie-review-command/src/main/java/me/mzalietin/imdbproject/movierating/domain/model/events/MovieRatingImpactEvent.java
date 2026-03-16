package me.mzalietin.imdbproject.movierating.domain.model.events;

public interface MovieRatingImpactEvent {

    Integer absoluteRatingImpact();

    Integer reviewsCountImpact();
}
