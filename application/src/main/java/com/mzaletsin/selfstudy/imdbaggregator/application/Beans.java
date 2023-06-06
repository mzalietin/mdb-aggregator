package com.mzaletsin.selfstudy.imdbaggregator.application;

import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.PersistenceConfig;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.SaveReviews;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.impl.SaveReviewsUC;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@Import(PersistenceConfig.class)
public class Beans {

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public SaveReviews saveReviews(MovieDataAccess movieDataAccess,
                                   MovieReviewDataAccess movieReviewDataAccess) {
         return new SaveReviewsUC(
             validator(),
             movieDataAccess,
             movieReviewDataAccess
         );
    }
}
