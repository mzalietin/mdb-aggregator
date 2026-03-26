package me.mzalietin.mdbproject.queryservice.domain.service.spi;

import java.math.BigDecimal;
import me.mzalietin.mdbproject.queryservice.domain.model.Movie;
import me.mzalietin.mdbproject.queryservice.domain.model.MovieWithUserRating;
import me.mzalietin.mdbproject.queryservice.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReadOperations {

    /*
        Movie rating by movie name
     */
    Mono<BigDecimal> movieRatingByName(String name);

    /*
        Top movies with the highest rating
     */
    Flux<Movie> topMovies(Integer limit);

    /*
       Top favorite user movies by username.
     */
    Flux<MovieWithUserRating> topByUser(String username, Integer limit);

    /*
        User info by username
     */
    Mono<User> userInfo(String username);
}
