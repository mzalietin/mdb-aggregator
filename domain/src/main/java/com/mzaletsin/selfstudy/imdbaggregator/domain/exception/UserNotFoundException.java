package com.mzaletsin.selfstudy.imdbaggregator.domain.exception;

public class UserNotFoundException extends RuntimeException {
    private final String username;

    public UserNotFoundException(final String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
