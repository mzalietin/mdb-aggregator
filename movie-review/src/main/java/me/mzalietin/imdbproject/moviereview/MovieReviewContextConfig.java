package me.mzalietin.imdbproject.moviereview;

import me.mzalietin.imdbproject.moviereview.domain.service.MovieReviewUseCasesInteractor;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewUseCases;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@PropertySource("classpath:movie-review-context.properties")
public class MovieReviewContextConfig {

    @Bean
    public MovieReviewUseCases movieReviewUseCases(MovieReviewDataAccess dataAccess) {
        return new MovieReviewUseCasesInteractor(dataAccess);
    }
}
