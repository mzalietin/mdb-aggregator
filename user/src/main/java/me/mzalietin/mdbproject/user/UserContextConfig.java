package me.mzalietin.mdbproject.user;

import me.mzalietin.mdbproject.user.domain.service.UserUseCasesInteractor;
import me.mzalietin.mdbproject.user.domain.service.spi.UserDataAccess;
import me.mzalietin.mdbproject.user.domain.service.spi.UserUseCases;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@PropertySource("classpath:user-context.properties")
public class UserContextConfig {

    @Bean
    public UserUseCases userUseCases(UserDataAccess dataAccess) {
        return new UserUseCasesInteractor(dataAccess);
    }
}
