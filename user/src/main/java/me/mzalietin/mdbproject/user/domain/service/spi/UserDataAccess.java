package me.mzalietin.mdbproject.user.domain.service.spi;

import me.mzalietin.mdbproject.user.domain.model.User;

public interface UserDataAccess {

    void save(User user);

    User getByUsername(String username);

    void delete(String username);
}
