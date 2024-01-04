package me.mzalietin.imdbproject.moviereview.config;

import me.mzalietin.imdbproject.moviereview.core.usecase.MovieReviewUseCasesInteractor;
import me.mzalietin.imdbproject.moviereview.core.usecase.ports.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.core.usecase.ports.MovieReviewUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieReviewBeans {

    @Bean
    public MovieReviewUseCases movieReviewUseCases(MovieReviewDataAccess dataAccess) {
        return new MovieReviewUseCasesInteractor(dataAccess);
    }
}
