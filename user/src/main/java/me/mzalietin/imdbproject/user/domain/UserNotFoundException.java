package me.mzalietin.imdbproject.user.domain;

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
