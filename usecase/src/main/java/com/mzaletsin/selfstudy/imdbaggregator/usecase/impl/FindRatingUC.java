package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.FindRating;

final class FindRatingUC implements FindRating {
    private final MovieDataAccess movieDataAccess;

    FindRatingUC(MovieDataAccess movieDataAccess) {
        this.movieDataAccess = movieDataAccess;
    }

    @Override
    public Double find(String movieName) {
        var movie = movieDataAccess.getByName(movieName);
        return movie.getRating().doubleValue();
    }
}
