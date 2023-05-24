package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
}
