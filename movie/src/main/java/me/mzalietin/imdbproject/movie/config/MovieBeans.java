package me.mzalietin.imdbproject.movie.config;

import me.mzalietin.imdbproject.movie.core.usecase.MovieUseCasesInteractor;
import me.mzalietin.imdbproject.movie.core.usecase.ports.MovieDataAccess;
import me.mzalietin.imdbproject.movie.core.usecase.ports.MovieUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieBeans {

    @Bean
    public MovieUseCases movieUseCases(MovieDataAccess movieDataAccess) {
        return new MovieUseCasesInteractor(movieDataAccess);
    }
}
