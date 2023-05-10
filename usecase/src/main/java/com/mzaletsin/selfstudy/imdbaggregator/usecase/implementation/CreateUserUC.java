package com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.UserDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.CreateUser;
import jakarta.validation.Validator;

final class CreateUserUC extends BaseValidatingUseCase implements CreateUser {
    private final UserDataAccess userDataAccess;

    public CreateUserUC(Validator validator,
                        UserDataAccess userDataAccess) {
        super(validator);
        this.userDataAccess = userDataAccess;
    }

    @Override
    public Integer create(User user) {
        validate(user);
        return userDataAccess.save(user);
    }
}
