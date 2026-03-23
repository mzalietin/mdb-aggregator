package me.mzalietin.mdbproject.user.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record User(
    @NotNull String username,
    @NotNull String firstName,
    @NotNull String lastName,
    @NotNull @Positive Integer age) {

    public me.mzalietin.mdbproject.user.domain.model.User toModel() {
        return new me.mzalietin.mdbproject.user.domain.model.User(username, firstName, lastName, age);
    }
}
