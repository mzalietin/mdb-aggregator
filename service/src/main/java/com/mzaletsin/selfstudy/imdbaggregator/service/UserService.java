package com.mzaletsin.selfstudy.imdbaggregator.service;

import com.mzaletsin.selfstudy.imdbaggregator.models.entity.User;

public interface UserService {

    Integer createUser(User user);

    User getUser(String username);

    void deleteUser(String username);
}
