package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import java.util.List;
import java.util.Optional;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, MovieReviewKey> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")})
    Optional<MovieReviewEntity> findById(MovieReviewKey id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    List<MovieReviewEntity> findByUsername(String username);
}
