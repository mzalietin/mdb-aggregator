package me.mzalietin.imdbproject.moviereview.infrastructure.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "movie-reviews", path = "movie-reviews")
public interface MovieReviewRepository
    extends PagingAndSortingRepository<MovieReviewEntity, MovieReviewId>, CrudRepository<MovieReviewEntity, MovieReviewId> {

}
