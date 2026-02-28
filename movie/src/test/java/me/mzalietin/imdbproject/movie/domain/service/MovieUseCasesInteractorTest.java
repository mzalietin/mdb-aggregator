package me.mzalietin.imdbproject.movie.domain.service;

import static me.mzalietin.imdbproject.movie.domain.model.DomainTestFixtures.MOVIE_ID;
import static me.mzalietin.imdbproject.movie.domain.model.DomainTestFixtures.initialMovie;
import static me.mzalietin.imdbproject.movie.domain.model.DomainTestFixtures.testMovie;

import java.util.Collection;
import java.util.List;
import me.mzalietin.imdbproject.movie.domain.model.Movie;
import me.mzalietin.imdbproject.movie.domain.service.spi.MovieDataAccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MovieUseCasesInteractorTest {

    MovieUseCasesInteractor useCasesInteractor = new MovieUseCasesInteractor(new DataAccessMock());

    @Test
    void test_create_movie() {
        var generatedId = useCasesInteractor.create(initialMovie());
        Assertions.assertEquals(MOVIE_ID, generatedId);
    }

    private static final class DataAccessMock implements MovieDataAccess {
        @Override
        public String save(final Movie movie) {
            return MOVIE_ID;
        }

        @Override
        public Movie getByName(final String name) {
            return testMovie();
        }

        @Override
        public Movie getById(final String id) {
            return testMovie();
        }

        @Override
        public Collection<Movie> getTopRated(final Integer limit) {
            return List.of(testMovie(), initialMovie());
        }

        @Override
        public Collection<Movie> getTopRated(final Integer limit, final String username) {
            return List.of(testMovie());
        }
    };
}
