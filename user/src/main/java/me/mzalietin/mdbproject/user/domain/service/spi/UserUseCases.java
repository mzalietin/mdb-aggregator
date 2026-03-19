package me.mzalietin.mdbproject.user.domain.service.spi;

import me.mzalietin.mdbproject.user.domain.model.User;
import me.mzalietin.mdbproject.user.domain.model.UserNotFoundException;

public interface UserUseCases {

    void create(User user);

    User find(String username) throws UserNotFoundException;

    void delete(String username);
}
