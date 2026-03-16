package me.mzalietin.imdbproject.movierating.application;

import java.util.Collection;
import me.mzalietin.imdbproject.movierating.domain.model.events.MovieRatingImpactEvent;

public interface MovieUseCases {

    void updateRating(String movieId, Collection<MovieRatingImpactEvent> impactEvents);
}
