package me.mzalietin.mdbproject.user.infrastructure.repo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mzalietin.mdbproject.user.domain.model.User;

@Entity
@Table(name = "mdb_user")
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

    public User toModel() {
        return new User(this.username, this.firstName, this.lastName, this.age);
    }

    public UserEntity(User user) {
        this.username = user.username();
        this.firstName = user.firstName();
        this.lastName = user.lastName();
        this.age = user.age();
    }
}
