package me.mzalietin.imdbproject.user.application;

import me.mzalietin.imdbproject.user.domain.service.UserUseCasesInteractor;
import me.mzalietin.imdbproject.user.domain.service.spi.UserDataAccess;
import me.mzalietin.imdbproject.user.domain.service.spi.UserUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeans {

    @Bean
    public UserUseCases userUseCases(UserDataAccess dataAccess) {
        return new UserUseCasesInteractor(dataAccess);
    }
}
