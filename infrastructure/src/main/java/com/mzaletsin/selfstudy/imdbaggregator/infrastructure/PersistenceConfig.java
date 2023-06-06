package com.mzaletsin.selfstudy.imdbaggregator.infrastructure;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.impl.MovieDao;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.impl.MovieReviewDao;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.impl.UserDao;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository.MovieRepository;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository.MovieReviewRepository;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository.UserRepository;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.UserDataAccess;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository"
)
@EntityScan("com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity")
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
