package me.mzalietin.mdbproject.movie.infrastructure.rest;

import jakarta.validation.Valid;
import me.mzalietin.mdbproject.movie.application.MovieUseCases;
import me.mzalietin.mdbproject.movie.infrastructure.rest.dto.IdResponse;
import me.mzalietin.mdbproject.movie.infrastructure.rest.dto.Movie;
import me.mzalietin.mdbproject.movie.infrastructure.rest.dto.NewMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieApi {

    @Autowired
    MovieUseCases movieUseCases;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> findMovieInfoByName(@RequestParam("name") String name) {
        return movieUseCases.findByName(name).map(Movie::new).map(movie -> ResponseEntity.ok().body(movie))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createMovie(@RequestBody @Valid NewMovie newMovie) {
        var movieId = movieUseCases.create(newMovie.name(), newMovie.releaseDate());
        return new IdResponse(movieId);
    }
}
