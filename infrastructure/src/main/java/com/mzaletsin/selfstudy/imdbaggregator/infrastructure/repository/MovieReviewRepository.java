package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.MovieReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, MovieReviewId> {

    Integer countByMovieId(String movieId);
}
