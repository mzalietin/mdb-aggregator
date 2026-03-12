package me.mzalietin.imdbproject.moviereview.infrastructure.repo;

import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends CrudRepository<MovieReviewEntity, MovieReviewKey> {

}
