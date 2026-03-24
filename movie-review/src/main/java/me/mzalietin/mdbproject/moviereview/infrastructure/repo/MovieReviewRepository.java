package me.mzalietin.mdbproject.moviereview.infrastructure.repo;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import java.util.Collection;
import java.util.Optional;
import me.mzalietin.mdbproject.moviereview.domain.model.MovieReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, MovieReviewKey> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")})
    @Query("SELECT mr FROM MovieReviewEntity mr WHERE mr.username = ?1 AND mr.movieId = ?2")
    Optional<MovieReviewEntity> findByUsernameAndMovieIdForUpdate(String username, String movieId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT mr FROM MovieReviewEntity mr WHERE mr.username = ?1")
    Collection<MovieReviewEntity> findByUsernameForUpdate(String username);
}
