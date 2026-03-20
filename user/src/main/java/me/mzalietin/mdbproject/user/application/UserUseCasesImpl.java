package me.mzalietin.mdbproject.user.application;

import me.mzalietin.mdbproject.user.domain.service.spi.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUseCasesImpl implements UserUseCases {

    @Autowired
    EventStore eventStore;

    @Override
    public void deleteUser(final String username) {
        //todo delete in DB

        eventStore.sendDeleted(username);
    }
}
