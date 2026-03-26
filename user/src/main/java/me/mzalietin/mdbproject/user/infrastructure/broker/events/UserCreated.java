package me.mzalietin.mdbproject.user.infrastructure.broker.events;

public record UserCreated(String firstName, String lastName, Integer age) {
}
