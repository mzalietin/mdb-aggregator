package me.mzalietin.imdbproject.moviereview.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, MovieReviewId> {

    Integer countByMovieId(String movieId);
}
