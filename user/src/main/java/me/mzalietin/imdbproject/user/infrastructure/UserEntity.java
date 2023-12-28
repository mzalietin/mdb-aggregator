package me.mzalietin.imdbproject.user.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import me.mzalietin.imdbproject.user.domain.User;

@Entity
@Table(name = "imdb_user")
public class UserEntity {
    @Id
    @Column(name = "username", length = 30, unique = true, nullable = false)
    private String username;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    public UserEntity() {
    }

    public UserEntity(final String username,
                final String firstName,
                final String lastName,
                final Integer age) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static UserEntity fromDomain(User domainUser) {
        return new UserEntity(
            domainUser.getUsername(),
            domainUser.getFirstName(),
            domainUser.getLastName(),
            domainUser.getAge()
        );
    }

    public static User toDomain(UserEntity persistenceUser) {
        return new User(
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
        final UserEntity user = (UserEntity) o;
        return Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName)
            && Objects.equals(lastName, user.lastName) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, age);
    }
}
