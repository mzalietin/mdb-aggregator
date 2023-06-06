package com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.exception.UserNotFoundException;

public interface UserUseCases {

    void create(User user);

    User find(String username) throws UserNotFoundException;

    void delete(String username);
}
