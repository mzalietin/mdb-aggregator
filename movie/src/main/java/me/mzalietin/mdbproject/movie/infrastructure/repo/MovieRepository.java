package me.mzalietin.mdbproject.movie.infrastructure.repo;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Modifying
    @Query("UPDATE MovieEntity m SET m.averageRating = :averageRating, m.reviewsCount = :reviewsCount WHERE m.id = :id")
    int updateRating(
        @Param("id") Long id,
        @Param("averageRating") BigDecimal rating,
        @Param("reviewsCount") Integer reviewsCount
    );
}
