package com.mzaletsin.selfstudy.imdbaggregator.service;

import com.mzaletsin.selfstudy.imdbaggregator.models.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.models.entity.MovieReview;
import java.util.Collection;

public interface MovieRatingService {

    void addReview(MovieReview  review);

    Collection<Movie> getTopRated(Integer positions);

    Collection<Movie> getTopRatedByUser(Integer positions, String username);
}
