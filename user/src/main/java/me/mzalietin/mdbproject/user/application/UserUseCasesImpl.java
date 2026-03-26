package me.mzalietin.mdbproject.user.application;

import java.util.Optional;
import me.mzalietin.mdbproject.user.domain.model.User;
import me.mzalietin.mdbproject.user.domain.service.spi.EventStore;
import me.mzalietin.mdbproject.user.domain.service.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUseCasesImpl implements UserUseCases {

    @Autowired
    EventStore eventStore;

    @Autowired
    UserDataAccess userDataAccess;

    @Override
    public Optional<User> get(final String username) {
        return userDataAccess.getUser(username);
    }

    @Override
    @Transactional("transactionManager")
    public void create(final User user) {
        eventStore.sendCreated(user);
        userDataAccess.createUser(user);
    }

    @Override
    @Transactional("transactionManager")
    public void delete(final String username) {
        eventStore.sendDeleted(username);
        userDataAccess.deleteUser(username);
    }
}
