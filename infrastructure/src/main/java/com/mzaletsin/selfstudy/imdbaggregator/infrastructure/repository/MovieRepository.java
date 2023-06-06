package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.Movie;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    Optional<Movie> findByName(String name);
}
