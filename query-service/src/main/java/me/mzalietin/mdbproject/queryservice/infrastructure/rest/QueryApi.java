package me.mzalietin.mdbproject.queryservice.infrastructure.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.BiFunction;
import me.mzalietin.mdbproject.queryservice.domain.model.Movie;
import me.mzalietin.mdbproject.queryservice.domain.model.MovieWithUserRating;
import me.mzalietin.mdbproject.queryservice.domain.model.User;
import me.mzalietin.mdbproject.queryservice.domain.service.spi.ReadOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class QueryApi {

    @Autowired
    ReadOperations readOperations;

    @GetMapping(path = "/movies/rating", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> movieRating(@RequestParam("name") String name) {
        return readOperations.movieRatingByName(name).map(BigDecimal::toString);
    }

    @GetMapping(path = "/movies/top/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ListResponse<Movie>> topMovies(@PathVariable("limit") Integer limit) {
        return readOperations.topMovies(limit)
            .reduce(new ListResponse<>(new ArrayList<>()), reducer());
    }

    @GetMapping(path = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> userInfo(@PathVariable("username") String username) {
        return readOperations.userInfo(username);
    }

    @GetMapping(path = "/movie-reviews/{username}/top/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ListResponse<MovieWithUserRating>> topByUser(@PathVariable("username") String username, @PathVariable("limit") Integer limit) {
        return readOperations.topByUser(username, limit)
            .reduce(new ListResponse<>(new ArrayList<>()), reducer());
    }

    private static <T> BiFunction<ListResponse<T>, T, ListResponse<T>> reducer() {
        return (resp, obj) -> {
            resp.result().add(obj);
            return resp;
        };
    }
}
