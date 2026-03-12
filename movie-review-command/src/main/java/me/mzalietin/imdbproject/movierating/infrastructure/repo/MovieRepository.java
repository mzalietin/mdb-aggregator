package me.mzalietin.imdbproject.movierating.infrastructure.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, String> {

}
