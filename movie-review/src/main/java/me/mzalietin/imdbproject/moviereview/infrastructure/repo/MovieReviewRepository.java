package me.mzalietin.imdbproject.moviereview.infrastructure.repo;

import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, MovieReviewKey> {

}
