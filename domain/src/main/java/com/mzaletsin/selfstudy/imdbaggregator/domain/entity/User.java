package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

public class User {
    private final Integer id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final Integer age;

    public User(String username, String firstName, String lastName, Integer age) {
        this(null, username, firstName, lastName, age);
    }

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
