package me.mzalietin.imdbproject.moviereview.infrastructure;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, MovieReviewId> {

    Integer countByMovieId(String movieId);
}
