package me.mzalietin.imdbproject.movie.infrastructure.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {

    Optional<MovieEntity> findByName(String name);
}
