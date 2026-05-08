package me.mzalietin.mdbproject.moviereview.infrastructure.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import me.mzalietin.mdbproject.moviereview.application.MovieReviewUseCases;
import me.mzalietin.mdbproject.moviereview.infrastructure.rest.dto.CreateReview;
import me.mzalietin.mdbproject.moviereview.infrastructure.rest.dto.UpdateReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-reviews")
@Validated
public class MovieReviewApi {

    @Autowired
    MovieReviewUseCases reviewUseCases;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody @Valid CreateReview review) {
        reviewUseCases.create(review.toModel());

    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReview(@PathVariable("id") @NotNull Long id, @RequestBody @Valid UpdateReview update) {
        reviewUseCases.update(id, update.rating(), update.comment());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteReview(@PathVariable("id") @NotNull Long id) {
        reviewUseCases.delete(id);
    }
}
