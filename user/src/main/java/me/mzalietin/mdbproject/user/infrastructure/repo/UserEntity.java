package me.mzalietin.mdbproject.user.infrastructure.repo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.mzalietin.mdbproject.user.domain.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "mdb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;

    public UserEntity(User user) {
        this.username = user.username();
        this.firstName = user.firstName();
        this.lastName = user.lastName();
        this.age = user.age();
    }
}
