package me.mzalietin.mdbproject.user.domain.service.spi;

import me.mzalietin.mdbproject.user.domain.model.User;

public interface EventStore {

    void sendCreated(User user);

    void sendDeleted(String username);
}
