package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.MOVIE_ID;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.testMovieReviews;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.PersistenceConfig;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.MovieReviewId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = PersistenceConfig.class)
class MovieReviewRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MovieReviewRepository repository;

    @Test
    void should_store_reviews() {
        var reviews = MovieReview.fromDomain(testMovieReviews());

        repository.saveAllAndFlush(reviews);

        reviews.stream()
            .map(r -> new MovieReviewId(r.getUsername(), r.getMovieId()))
            .forEach(id -> assertNotNull(entityManager.find(MovieReview.class, id)));
    }

    @Test
    void should_count_by_movie_id() {
        var reviews = MovieReview.fromDomain(testMovieReviews());
        reviews.forEach(entityManager::persistAndFlush);

        var result = repository.countByMovieId(MOVIE_ID);

        assertEquals(5, result);
    }
}
