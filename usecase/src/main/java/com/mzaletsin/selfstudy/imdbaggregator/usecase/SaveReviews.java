package com.mzaletsin.selfstudy.imdbaggregator.usecase;

import java.util.Collection;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReview;

public interface SaveReviews {

    void save(final Collection<MovieReview> reviews);
}
