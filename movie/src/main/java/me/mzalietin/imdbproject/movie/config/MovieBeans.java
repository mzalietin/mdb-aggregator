package me.mzalietin.imdbproject.movie.config;

import me.mzalietin.imdbproject.movie.core.usecase.MovieUseCasesInteractor;
import me.mzalietin.imdbproject.movie.core.usecase.ports.MovieDataAccess;
import me.mzalietin.imdbproject.movie.core.usecase.ports.MovieUseCases;
import me.mzalietin.imdbproject.movie.gateway.dataprovider.MovieDao;
import me.mzalietin.imdbproject.movie.gateway.dataprovider.MovieRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieBeans {

    @Bean
    public MovieDataAccess movieDataAccess(MovieRepository repository) {
        return new MovieDao(repository);
    }

    @Bean
    public MovieUseCases movieUseCases(MovieDataAccess movieDataAccess) {
        return new MovieUseCasesInteractor(movieDataAccess);
    }
}
