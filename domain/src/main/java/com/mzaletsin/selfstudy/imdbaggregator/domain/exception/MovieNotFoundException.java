package com.mzaletsin.selfstudy.imdbaggregator.domain.exception;

public class MovieNotFoundException extends RuntimeException {
    private final String id;

    public MovieNotFoundException(final String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
