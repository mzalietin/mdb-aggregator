package com.mzaletsin.selfstudy.imdbaggregator.infrastructure;

import com.mzaletsin.selfstudy.imdbaggregator.models.entity.Movie;

public interface MovieDao {

    String save(Movie movie);

    Movie getByName(String name);

    Movie getById(String id);
}
