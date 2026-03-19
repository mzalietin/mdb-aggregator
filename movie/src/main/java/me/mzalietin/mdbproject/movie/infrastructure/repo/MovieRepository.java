package me.mzalietin.mdbproject.movie.infrastructure.repo;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, String>, CrudRepository<MovieEntity, String> {

    Optional<MovieEntity> findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("UPDATE MovieEntity m SET m.averageRating = :averageRating, m.reviewsCount = :reviewsCount WHERE m.id = :id")
    int updateRating(
        @Param("id") String id,
        @Param("averageRating") BigDecimal rating,
        @Param("reviewsCount") Integer reviewsCount
    );
}
