package com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;

public interface UserDataAccess {

    void save(User user);

    User getByUsername(String username);

    void delete(String username);
}
