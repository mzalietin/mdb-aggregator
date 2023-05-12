package com.mzaletsin.selfstudy.imdbaggregator.infrastructure;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.UserDataAccess;

public class UserDataRepository implements UserDataAccess {

    @Override
    public Integer save(User user) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public void delete(String username) {

    }
}
