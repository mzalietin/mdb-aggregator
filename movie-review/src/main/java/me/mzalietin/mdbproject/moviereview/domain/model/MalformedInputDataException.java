package me.mzalietin.mdbproject.moviereview.domain.model;

public class MalformedInputDataException extends RuntimeException {
    public MalformedInputDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
