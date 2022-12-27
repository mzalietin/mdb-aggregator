package com.mzaletsin.selfstudy.imdbaggregator.infrastructure;

import com.mzaletsin.selfstudy.imdbaggregator.models.entity.MovieReview;

public interface MovieReviewDao {

    void save(MovieReview movieReview);
}
