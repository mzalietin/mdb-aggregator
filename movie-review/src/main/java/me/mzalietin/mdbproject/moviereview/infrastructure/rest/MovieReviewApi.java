package me.mzalietin.mdbproject.moviereview.infrastructure.rest;

import jakarta.validation.Valid;
import me.mzalietin.mdbproject.moviereview.application.MovieReviewUseCases;
import me.mzalietin.mdbproject.moviereview.infrastructure.rest.dto.MovieReview;
import me.mzalietin.mdbproject.moviereview.infrastructure.rest.dto.MovieReviewKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-reviews")
public class MovieReviewApi {

    @Autowired
    MovieReviewUseCases reviewUseCases;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody @Valid MovieReview review) {
        reviewUseCases.create(review.toModel());

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReview(@RequestBody @Valid MovieReview review) {
        reviewUseCases.update(review.toModel());
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReview(@RequestBody @Valid MovieReviewKey review) {
        reviewUseCases.delete(review.toModel());
    }
}
