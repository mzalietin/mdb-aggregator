package me.mzalietin.mdbproject.moviereview.infrastructure.rest;

import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ProblemDetail handleException(ResourceAlreadyExistsException e) {
        var detail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        detail.setTitle("Resource Already Exists");
        detail.setDetail(e.getMessage());
        return detail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleException(ResourceNotFoundException e) {
        var detail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        detail.setTitle("Resource Not Found");
        detail.setDetail(e.getMessage());
        return detail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleException(IllegalArgumentException e) {
        var detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setTitle("Malformed Request");
        detail.setDetail(e.getMessage());
        return detail;
    }
}
