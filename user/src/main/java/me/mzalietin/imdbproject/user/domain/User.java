package me.mzalietin.imdbproject.user.domain;

public class User {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final Integer age;

    public User(String username, String firstName, String lastName, Integer age) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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
