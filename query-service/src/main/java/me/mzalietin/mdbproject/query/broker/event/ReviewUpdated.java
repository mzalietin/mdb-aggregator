package me.mzalietin.mdbproject.query.broker.event;

public record ReviewUpdated(Integer oldRating, String oldComment, Integer newRating, String newComment) {
}
