package com.mzaletsin.selfstudy.imdbaggregator.application.config;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.SaveReviews;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation.SaveReviewsUC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Collection;

@Configuration
public class Beans {

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MovieDataAccess movieDataAccess() {
        return new MovieDataAccess() {
            @Override
            public String save(Movie movie) {
                return null;
            }

            @Override
            public Movie getByName(String name) {
                return null;
            }

            @Override
            public Movie getById(String id) {
                return null;
            }

            @Override
            public Collection<Movie> getTopRated(Integer count) {
                return null;
            }

            @Override
            public Collection<Movie> getTopRated(Integer count, String username) {
                return null;
            }
        };
    }

    @Bean
    public MovieReviewDataAccess movieReviewDataAccess() {
        return new MovieReviewDataAccess() {
            @Override
            public void save(MovieReviews reviews) {

            }

            @Override
            public Integer countByMovieId(String movieId) {
                return null;
            }
        };
    }

    @Bean
    public SaveReviews saveReviews() {
         return new SaveReviewsUC(
             validator(),
             movieDataAccess(),
             movieReviewDataAccess()
         );
    }
}
