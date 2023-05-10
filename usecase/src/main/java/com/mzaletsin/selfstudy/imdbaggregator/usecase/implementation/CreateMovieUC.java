package com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.CreateMovie;

final class CreateMovieUC implements CreateMovie {
    private final MovieDataAccess movieDataAccess;

    public CreateMovieUC(MovieDataAccess movieDataAccess) {
        this.movieDataAccess = movieDataAccess;
    }

    @Override
    public String create(Movie movie) {
        return movieDataAccess.save(movie);
    }
}
