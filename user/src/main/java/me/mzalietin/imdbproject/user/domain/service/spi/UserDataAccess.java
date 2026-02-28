package me.mzalietin.imdbproject.user.domain.service.spi;

import me.mzalietin.imdbproject.user.domain.model.User;

public interface UserDataAccess {

    void save(User user);

    User getByUsername(String username);

    void delete(String username);
}
