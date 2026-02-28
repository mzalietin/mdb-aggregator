package me.mzalietin.imdbproject.movie.application;

import me.mzalietin.imdbproject.movie.domain.service.MovieUseCasesInteractor;
import me.mzalietin.imdbproject.movie.domain.service.spi.MovieDataAccess;
import me.mzalietin.imdbproject.movie.domain.service.spi.MovieUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieBeans {

    @Bean
    public MovieUseCases movieUseCases(MovieDataAccess movieDataAccess) {
        return new MovieUseCasesInteractor(movieDataAccess);
    }
}
