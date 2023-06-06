package com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.exception.MovieNotFoundException;
import java.util.Collection;

public interface MovieUseCases {

    String create(Movie movie);

    Double findRating(String movieName) throws MovieNotFoundException;

    Collection<Movie> getTopRated(Integer count);

    Collection<Movie> getTopRatedByUser(Integer count, String username);
}
