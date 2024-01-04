package me.mzalietin.imdbproject.application;

import me.mzalietin.imdbproject.movie.core.usecase.ports.MovieDataAccess;
import me.mzalietin.imdbproject.movie.gateway.dataprovider.MovieDao;
import me.mzalietin.imdbproject.movie.gateway.dataprovider.MovieRepository;
import me.mzalietin.imdbproject.moviereview.core.usecase.ports.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.gateway.dataprovider.MovieReviewDao;
import me.mzalietin.imdbproject.moviereview.gateway.dataprovider.MovieReviewRepository;
import me.mzalietin.imdbproject.user.core.usecase.ports.UserDataAccess;
import me.mzalietin.imdbproject.user.gateway.dataprovider.UserDao;
import me.mzalietin.imdbproject.user.gateway.dataprovider.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "me.mzalietin.imdbproject.*.gateway.dataprovider"
)
@EntityScan("me.mzalietin.imdbproject.*.gateway.dataprovider")
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
