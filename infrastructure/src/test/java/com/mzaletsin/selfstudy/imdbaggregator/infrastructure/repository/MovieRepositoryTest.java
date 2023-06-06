package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.INITIAL_MOVIE_RATING;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.MOVIE_NAME;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.MOVIE_RATING;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.RELEASE_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.PersistenceConfig;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.Movie;
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
class MovieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MovieRepository repository;

    @Test
    void should_store_a_movie() {
        var movie = new Movie(MOVIE_NAME, RELEASE_DATE, MOVIE_RATING);

        repository.saveAndFlush(movie);

        assertNotNull(entityManager.find(Movie.class, movie.getId()));
    }

    @Test
    void should_find_movie_by_id() {
        var movie = new Movie(MOVIE_NAME, RELEASE_DATE, INITIAL_MOVIE_RATING);
        var movieId = entityManager.persistAndFlush(movie).getId();

        var foundMovie = repository.findById(movieId);

        assertTrue(foundMovie.isPresent());
        assertEquals(movie, foundMovie.get());
    }

    @Test
    void should_find_movie_by_name() {
        var movie = new Movie(MOVIE_NAME, RELEASE_DATE, INITIAL_MOVIE_RATING);
        entityManager.persistAndFlush(movie);

        var foundMovie = repository.findByName(MOVIE_NAME);

        assertTrue(foundMovie.isPresent());
        assertEquals(movie, foundMovie.get());
    }
}
