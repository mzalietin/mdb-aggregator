package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "movie-reviews", path = "movie-reviews")
@Transactional
public interface MovieReviewRepository extends PagingAndSortingRepository<MovieReviewEntity, MovieReviewKey>,
    CrudRepository<MovieReviewEntity, MovieReviewKey> {

}
