package com.mzaletsin.selfstudy.imdbaggregator.domain.port;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;

public interface UserDataAccess {

    Integer save(User user);

    User getByUsername(String username);

    void delete(String username);
}
