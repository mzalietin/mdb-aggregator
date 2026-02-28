package me.mzalietin.imdbproject.user.domain.service.spi;

import me.mzalietin.imdbproject.user.domain.model.User;
import me.mzalietin.imdbproject.user.domain.model.UserNotFoundException;

public interface UserUseCases {

    void create(User user);

    User find(String username) throws UserNotFoundException;

    void delete(String username);
}
