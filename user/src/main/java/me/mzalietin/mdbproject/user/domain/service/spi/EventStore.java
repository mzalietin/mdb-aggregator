package me.mzalietin.mdbproject.user.domain.service.spi;

public interface EventStore {

    void sendDeleted(String username);
}
