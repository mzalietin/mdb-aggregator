package me.mzalietin.imdbproject.movie;

import me.mzalietin.imdbproject.movie.domain.service.MovieUseCasesInteractor;
import me.mzalietin.imdbproject.movie.domain.service.spi.MovieDataAccess;
import me.mzalietin.imdbproject.movie.domain.service.spi.MovieUseCases;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@PropertySource("classpath:movie-context.properties")
public class MovieContextConfig {

    @Bean
    public MovieUseCases movieUseCases(MovieDataAccess movieDataAccess) {
        return new MovieUseCasesInteractor(movieDataAccess);
    }
}
