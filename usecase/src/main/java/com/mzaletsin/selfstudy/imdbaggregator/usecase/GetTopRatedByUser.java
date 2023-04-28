package com.mzaletsin.selfstudy.imdbaggregator.usecase;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;

import java.util.Collection;

public interface GetTopRatedByUser {

    Collection<Movie> get(Integer amount, String username);
}
