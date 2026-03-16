package me.mzalietin.imdbproject.movierating.application;

import java.util.Collection;
import me.mzalietin.imdbproject.movierating.domain.model.events.MovieRatingImpactEvent;
import me.mzalietin.imdbproject.movierating.domain.service.spi.MovieDataAccess;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieUseCasesImpl implements MovieUseCases {

    @Autowired
    MovieDataAccess movieDataAccess;

    @Override
    public void updateRating(final String movieId, final Collection<MovieRatingImpactEvent> impactEvents) {

        //TODO Transactional update to the Movie

    }
}
