package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "imdb_user")
public class User {
    @Id
    @Column(name = "username", length = 30, unique = true, nullable = false)
    private String username;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    public User() {
    }

    public User(final String username,
                final String firstName,
                final String lastName,
                final Integer age) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static User fromDomain(com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User domainUser) {
        return new User(
            domainUser.getUsername(),
            domainUser.getFirstName(),
            domainUser.getLastName(),
            domainUser.getAge()
        );
    }

    public static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User toDomain(User persistenceUser) {
        return new com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User(
            persistenceUser.getUsername(),
            persistenceUser.getFirstName(),
            persistenceUser.getLastName(),
            persistenceUser.getAge()
        );
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

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName)
            && Objects.equals(lastName, user.lastName) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, age);
    }
}
