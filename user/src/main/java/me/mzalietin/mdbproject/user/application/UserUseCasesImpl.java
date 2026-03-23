package me.mzalietin.mdbproject.user.application;

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
    @Transactional("transactionManager")
    public void create(final User user) {
        userDataAccess.createUser(user);
    }

    @Override
    @Transactional("transactionManager")
    public void delete(final String username) {
        userDataAccess.deleteUser(username);
        eventStore.sendDeleted(username);
    }
}
