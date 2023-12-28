package me.mzalietin.imdbproject.application;

import me.mzalietin.imdbproject.movie.infrastructure.MovieDao;
import me.mzalietin.imdbproject.movie.infrastructure.MovieRepository;
import me.mzalietin.imdbproject.movie.usecase.ports.MovieDataAccess;
import me.mzalietin.imdbproject.moviereview.infrastructure.MovieReviewDao;
import me.mzalietin.imdbproject.moviereview.infrastructure.MovieReviewRepository;
import me.mzalietin.imdbproject.moviereview.usecase.ports.MovieReviewDataAccess;
import me.mzalietin.imdbproject.user.infrastructure.UserDao;
import me.mzalietin.imdbproject.user.infrastructure.UserRepository;
import me.mzalietin.imdbproject.user.usecase.ports.UserDataAccess;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "me.mzalietin.imdbproject.*.infrastructure"
)
@EntityScan("me.mzalietin.imdbproject.*.infrastructure")
public class PersistenceConfig {

    @Bean
    public MovieDataAccess movieDataAccess(MovieRepository repository) {
        return new MovieDao(repository);
    }

    @Bean
    public MovieReviewDataAccess movieReviewDataAccess(MovieReviewRepository repository) {
        return new MovieReviewDao(repository);
    }

    @Bean
    public UserDataAccess userDataAccess(UserRepository repository) {
        return new UserDao(repository);
    }
}
