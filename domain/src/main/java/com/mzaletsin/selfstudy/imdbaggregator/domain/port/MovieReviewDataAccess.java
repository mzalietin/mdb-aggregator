package com.mzaletsin.selfstudy.imdbaggregator.domain.port;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReview;

public interface MovieReviewDataAccess {

    void save(MovieReview movieReview);

    Integer countByMovieId(String movieId);
}
