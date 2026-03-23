package me.mzalietin.mdbproject.user.application;

import java.util.Optional;
import me.mzalietin.mdbproject.user.domain.model.User;

public interface UserUseCases {

    Optional<User> get(String username);

    void create(User user);

    void delete(String username);
}
