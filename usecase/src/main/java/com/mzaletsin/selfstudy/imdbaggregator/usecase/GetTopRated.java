package com.mzaletsin.selfstudy.imdbaggregator.usecase;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;

import java.util.Collection;

public interface GetTopRated {

    Collection<Movie> get(Integer amount);
}
