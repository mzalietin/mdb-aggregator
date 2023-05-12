package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.implementation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
}
