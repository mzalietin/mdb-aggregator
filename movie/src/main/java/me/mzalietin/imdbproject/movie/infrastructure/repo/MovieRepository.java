package me.mzalietin.imdbproject.movie.infrastructure.repo;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, String>, CrudRepository<MovieEntity, String> {

    Optional<MovieEntity> findByName(@Param("name") String name);
}
