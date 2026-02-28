package me.mzalietin.imdbproject.moviereview.application;

import me.mzalietin.imdbproject.moviereview.domain.service.MovieReviewUseCasesInteractor;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieReviewBeans {

    @Bean
    public MovieReviewUseCases movieReviewUseCases(MovieReviewDataAccess dataAccess) {
        return new MovieReviewUseCasesInteractor(dataAccess);
    }
}
