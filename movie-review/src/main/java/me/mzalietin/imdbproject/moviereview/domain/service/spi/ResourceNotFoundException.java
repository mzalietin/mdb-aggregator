package me.mzalietin.imdbproject.moviereview.domain.service.spi;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
