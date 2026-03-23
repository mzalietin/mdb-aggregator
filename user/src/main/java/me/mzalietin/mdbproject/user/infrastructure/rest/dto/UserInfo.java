package me.mzalietin.mdbproject.user.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import me.mzalietin.mdbproject.user.domain.model.User;

public record UserInfo(
    @NotNull String username,
    @NotNull String firstName,
    @NotNull String lastName,
    @NotNull @Positive Integer age) {

    public User toModel() {
        return new User(username, firstName, lastName, age);
    }

    public UserInfo(User model) {
        this(model.username(), model.firstName(), model.firstName(), model.age());
    }
}
