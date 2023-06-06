package com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.exception.MovieNotFoundException;

public interface MovieReviewUseCases {

    void save(MovieReviews reviews) throws MovieNotFoundException;
}
