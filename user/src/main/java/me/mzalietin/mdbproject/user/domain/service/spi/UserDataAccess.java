package me.mzalietin.mdbproject.user.domain.service.spi;

import me.mzalietin.mdbproject.user.domain.model.User;

public interface UserDataAccess {

    void createUser(User user);

    void deleteUser(String username);
}
