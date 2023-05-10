package com.mzaletsin.selfstudy.imdbaggregator.usecase;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;

import java.util.Collection;

public interface GetTopRated {

    Collection<Movie> get(Integer count);

    Collection<Movie> getByUser(Integer count, String username);
}
