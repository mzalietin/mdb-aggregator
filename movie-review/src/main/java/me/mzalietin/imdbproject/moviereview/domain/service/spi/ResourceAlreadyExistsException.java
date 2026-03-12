package me.mzalietin.imdbproject.moviereview.domain.service.spi;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
