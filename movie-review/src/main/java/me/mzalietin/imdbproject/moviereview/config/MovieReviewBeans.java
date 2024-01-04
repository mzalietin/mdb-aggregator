package me.mzalietin.imdbproject.moviereview.config;

import me.mzalietin.imdbproject.moviereview.core.usecase.MovieReviewUseCasesInteractor;
import me.mzalietin.imdbproject.moviereview.core.usecase.ports.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.core.usecase.ports.MovieReviewUseCases;
import me.mzalietin.imdbproject.moviereview.gateway.dataprovider.MovieReviewDao;
import me.mzalietin.imdbproject.moviereview.gateway.dataprovider.MovieReviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieReviewBeans {

    @Bean
    public MovieReviewDataAccess movieReviewDataAccess(MovieReviewRepository repository) {
        return new MovieReviewDao(repository);
    }

    @Bean
    public MovieReviewUseCases movieReviewUseCases(MovieReviewDataAccess dataAccess) {
        return new MovieReviewUseCasesInteractor(dataAccess);
    }
}
