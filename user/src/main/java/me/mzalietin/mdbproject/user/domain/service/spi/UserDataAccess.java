package me.mzalietin.mdbproject.user.domain.service.spi;

import java.util.Optional;
import me.mzalietin.mdbproject.user.domain.model.User;

public interface UserDataAccess {

    Optional<User> getUser(String username);

    void createUser(User user);

    void deleteUser(String username);
}
