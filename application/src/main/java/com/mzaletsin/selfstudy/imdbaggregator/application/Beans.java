package com.mzaletsin.selfstudy.imdbaggregator.application;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.PersistenceConfig;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.CasesProvider;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.UserDataAccess;
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
    public CasesProvider casesProvider(MovieDataAccess movieDataAccess,
                                       UserDataAccess userDataAccess,
                                       MovieReviewDataAccess movieReviewDataAccess) {
         return new CasesProvider(movieDataAccess, userDataAccess, movieReviewDataAccess, validator());
    }
}
