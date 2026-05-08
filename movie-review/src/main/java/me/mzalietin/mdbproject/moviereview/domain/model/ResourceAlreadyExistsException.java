package me.mzalietin.mdbproject.moviereview.domain.model;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
