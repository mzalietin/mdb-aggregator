package com.mzaletsin.selfstudy.imdbaggregator.application;

import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.UserDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.PersistenceConfig;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.UseCaseFactory;
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
    public UseCaseFactory useCaseFactory(
        UserDataAccess userDataAccess,
        MovieDataAccess movieDataAccess,
        MovieReviewDataAccess movieReviewDataAccess) {

        return new UseCaseFactory(userDataAccess, movieDataAccess, movieReviewDataAccess, validator());
    }
}
