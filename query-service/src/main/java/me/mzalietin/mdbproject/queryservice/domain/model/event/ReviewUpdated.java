package me.mzalietin.mdbproject.queryservice.domain.model.event;

public record ReviewUpdated(Integer oldRating, String oldComment, Integer newRating, String newComment) {
}
