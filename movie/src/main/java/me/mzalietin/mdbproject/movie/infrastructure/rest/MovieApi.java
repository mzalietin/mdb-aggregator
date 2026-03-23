package me.mzalietin.mdbproject.movie.infrastructure.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import me.mzalietin.mdbproject.movie.application.MovieUseCases;
import me.mzalietin.mdbproject.movie.infrastructure.rest.dto.IdResponse;
import me.mzalietin.mdbproject.movie.infrastructure.rest.dto.ListResponse;
import me.mzalietin.mdbproject.movie.infrastructure.rest.dto.MovieInfo;
import me.mzalietin.mdbproject.movie.infrastructure.rest.dto.NewMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@Validated
public class MovieApi {

    @Autowired
    MovieUseCases movieUseCases;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieInfo> findMovieInfoByName(@RequestParam("name") String name) {
        return movieUseCases.findByName(name).map(MovieInfo::new).map(movie -> ResponseEntity.ok().body(movie))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/top/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse findTopMovies(@PathVariable("limit") @Min(1) @Max(500) Integer limit) {
        var responseList = movieUseCases.findTopMovies(limit).stream().map(MovieInfo::new).toList();
        return new ListResponse(responseList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createMovie(@RequestBody @Valid NewMovie newMovie) {
        var movieId = movieUseCases.create(newMovie.name(), newMovie.releaseDate());
        return new IdResponse(movieId);
    }
}
