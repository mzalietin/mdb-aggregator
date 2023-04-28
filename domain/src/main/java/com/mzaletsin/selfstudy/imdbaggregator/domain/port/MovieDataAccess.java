package com.mzaletsin.selfstudy.imdbaggregator.domain.port;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;

public interface MovieDataAccess {

    String save(Movie movie);

    Movie getByName(String name);

    Movie getById(String id);
}
