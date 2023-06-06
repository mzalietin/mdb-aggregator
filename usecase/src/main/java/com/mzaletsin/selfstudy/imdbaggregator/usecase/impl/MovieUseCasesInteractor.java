package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in.MovieUseCases;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieDataAccess;
import jakarta.validation.Validator;
import java.util.Collection;

public final class MovieUseCasesInteractor extends ValidatingInteractor implements MovieUseCases {
    private final MovieDataAccess movieDataAccess;

    public MovieUseCasesInteractor(final Validator validator,
                            final MovieDataAccess movieDataAccess) {
        super(validator);
        this.movieDataAccess = movieDataAccess;
    }

    @Override
    public String create(final Movie movie) {
        validate(movie);
        return movieDataAccess.save(movie);
    }

    @Override
    public Double findRating(final String movieName) {
        var movie = movieDataAccess.getByName(movieName);
        return movie.getRating().doubleValue();
    }

    @Override
    public Collection<Movie> getTopRated(final Integer count) {
        return movieDataAccess.getTopRated(count);
    }

    @Override
    public Collection<Movie> getTopRatedByUser(final Integer count, final String username) {
        return movieDataAccess.getTopRated(count, username);
    }
}
