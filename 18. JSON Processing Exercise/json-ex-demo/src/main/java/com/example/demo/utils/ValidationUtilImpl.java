package com.example.demo.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    public ValidationUtilImpl(){
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
        Set<ConstraintViolation<T>> validate = this.validator.validate(entity);
        // If the Set of errors is empty, then the entity does
        // not generate an error and it is valid and will return true
        boolean result = validate.isEmpty();
        return result;

        //short syntax return this.validator.validate(entity).isEmpty()

    }

    @Override
    public <T> Set<ConstraintViolation<T>> violations(T entity) {
        return this.validator.validate(entity);
    }
}
