package com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.GetTopRated;

import java.util.Collection;

final class GetTopRatedUC implements GetTopRated {
    private final MovieDataAccess movieDataAccess;

    GetTopRatedUC(MovieDataAccess movieDataAccess) {
        this.movieDataAccess = movieDataAccess;
    }

    @Override
    public Collection<Movie> get(Integer count) {
        return movieDataAccess.getTopRated(count);
    }

    @Override
    public Collection<Movie> getByUser(Integer count, String username) {
        return movieDataAccess.getTopRated(count, username);
    }
}
