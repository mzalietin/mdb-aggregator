package me.mzalietin.mdbproject.queryservice.infrastructure.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public Mono<ListResponse> topMovies(@PathVariable("limit") Integer limit) {
        return readOperations.topMovies(limit)
            .reduce(new ListResponse(new ArrayList<>()), (resp, movie) -> {
                resp.result().add(movie);
                return resp;
            });
    }

    @GetMapping(path = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> userInfo(@PathVariable("username") String username) {
        return readOperations.userInfo(username);
    }
}
