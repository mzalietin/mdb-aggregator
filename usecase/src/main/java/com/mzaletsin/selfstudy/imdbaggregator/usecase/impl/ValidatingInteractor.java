package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Set;

abstract class ValidatingInteractor {
    private final Validator validator;

    ValidatingInteractor(Validator validator) {
        this.validator = validator;
    }

    protected void validate(Object bean) throws ConstraintViolationException {
        Set<ConstraintViolation<Object>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Bean validation failed", violations);
        }
    }
}
