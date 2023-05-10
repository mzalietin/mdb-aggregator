package com.mzaletsin.selfstudy.imdbaggregator.usecase;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;

public interface SaveReviews {

    void save(MovieReviews reviews);
}
