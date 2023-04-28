package com.mzaletsin.selfstudy.imdbaggregator.domain.port;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReview;

import java.util.Collection;

public interface MovieReviewDataAccess {

    void save(Collection<MovieReview> movieReviews);

    Integer countByMovieId(String movieId);
}
