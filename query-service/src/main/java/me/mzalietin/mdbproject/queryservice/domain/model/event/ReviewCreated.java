package me.mzalietin.mdbproject.queryservice.domain.model.event;

public record ReviewCreated(String username, Long movieId, Integer rating, String comment) {
}
