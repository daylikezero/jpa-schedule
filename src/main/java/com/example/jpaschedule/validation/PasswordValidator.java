package com.example.jpaschedule.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public interface PasswordValidator extends ConstraintValidator<PasswordValid, String> {
    @Override
    default void initialize(PasswordValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext);
}
