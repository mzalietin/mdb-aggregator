package com.mzaletsin.selfstudy.imdbaggregator.domain.port;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;

public interface MovieReviewDataAccess {

    void save(MovieReviews reviews);

    Integer countByMovieId(String movieId);
}
