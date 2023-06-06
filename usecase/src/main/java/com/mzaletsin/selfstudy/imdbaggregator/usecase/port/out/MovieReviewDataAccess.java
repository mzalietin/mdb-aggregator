package com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;

public interface MovieReviewDataAccess {

    void save(MovieReviews reviews);

    Integer countByMovieId(String movieId);
}
