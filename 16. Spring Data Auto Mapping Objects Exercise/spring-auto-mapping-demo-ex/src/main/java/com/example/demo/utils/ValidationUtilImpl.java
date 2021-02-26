package com.example.demo.utils;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    // Comes from hibernate validator
    private Validator validator;

    public ValidationUtilImpl(){
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }
    @Override
    public <T> boolean isValid(T entity) {
        // Check if the validator is empty, if the set of constraints is empty
        // If the set is empty the entity is valid
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T entity) {
        // Returns all violations of the entity
        return this.validator.validate(entity);
    }
}

















