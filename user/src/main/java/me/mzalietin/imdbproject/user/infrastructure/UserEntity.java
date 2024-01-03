package me.mzalietin.imdbproject.user.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mzalietin.imdbproject.user.domain.User;

@Entity
@Table(name = "imdb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public static UserEntity fromDomain(User domainUser) {
        return new UserEntity(
            domainUser.username(),
            domainUser.firstName(),
            domainUser.lastName(),
            domainUser.age()
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
}
