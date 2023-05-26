package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Set;

abstract class BaseValidatingUseCase {
    private final Validator validator;

    BaseValidatingUseCase(Validator validator) {
        this.validator = validator;
    }

    protected void validate(Object bean) throws ConstraintViolationException {
        Set<ConstraintViolation<Object>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Bean validation failed", violations);
        }
    }
}
