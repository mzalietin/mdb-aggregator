package me.mzalietin.imdbproject.user.config;

import me.mzalietin.imdbproject.user.core.usecase.UserUseCasesInteractor;
import me.mzalietin.imdbproject.user.core.usecase.ports.UserDataAccess;
import me.mzalietin.imdbproject.user.core.usecase.ports.UserUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeans {

    @Bean
    public UserUseCases userUseCases(UserDataAccess dataAccess) {
        return new UserUseCasesInteractor(dataAccess);
    }
}
