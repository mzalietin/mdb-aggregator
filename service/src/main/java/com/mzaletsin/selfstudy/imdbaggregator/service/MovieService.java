package com.mzaletsin.selfstudy.imdbaggregator.service;

import com.mzaletsin.selfstudy.imdbaggregator.models.entity.Movie;
import java.util.Collection;

public interface MovieService {

    String createMovie(Movie movie);

    Movie getMovie(String name);
}
