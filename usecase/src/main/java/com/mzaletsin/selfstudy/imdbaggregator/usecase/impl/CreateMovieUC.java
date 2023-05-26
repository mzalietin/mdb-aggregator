package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.CreateMovie;
import jakarta.validation.Validator;

final class CreateMovieUC extends BaseValidatingUseCase implements CreateMovie {
    private final MovieDataAccess movieDataAccess;

    public CreateMovieUC(Validator validator,
                         MovieDataAccess movieDataAccess) {
        super(validator);
        this.movieDataAccess = movieDataAccess;
    }

    @Override
    public String create(Movie movie) {
        validate(movie);
        return movieDataAccess.save(movie);
    }
}
