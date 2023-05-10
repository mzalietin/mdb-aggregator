package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class User {
    private final Integer id;
    @Size(min = 3, max = 30)
    private final String username;
    @Size(min = 1, max = 30)
    private final String firstName;
    @Size(min = 1, max = 30)
    private final String lastName;
    @Min(12) @Max(130)
    private final Integer age;

    public User(Integer id, String username, String firstName, String lastName, Integer age) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }
}
