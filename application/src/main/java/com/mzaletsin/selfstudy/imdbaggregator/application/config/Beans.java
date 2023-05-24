package com.mzaletsin.selfstudy.imdbaggregator.application.config;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.MovieDataRepository;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository.MovieRepository;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.SaveReviews;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation.SaveReviewsUC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class Beans {

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    //TODO fix Unsatisfied dependency
    public MovieDataAccess movieDataAccess(MovieRepository repository) {
        return new MovieDataRepository(repository);
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
    public SaveReviews saveReviews(MovieDataAccess movieDataAccess) {
         return new SaveReviewsUC(
             validator(),
             movieDataAccess,
             movieReviewDataAccess()
         );
    }
}
