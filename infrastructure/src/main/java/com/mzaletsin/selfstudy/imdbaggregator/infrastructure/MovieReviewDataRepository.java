package com.mzaletsin.selfstudy.imdbaggregator.infrastructure;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;

public class MovieReviewDataRepository implements MovieReviewDataAccess {

    @Override
    public void save(MovieReviews reviews) {

    }

    @Override
    public Integer countByMovieId(String movieId) {
        return null;
    }
}
