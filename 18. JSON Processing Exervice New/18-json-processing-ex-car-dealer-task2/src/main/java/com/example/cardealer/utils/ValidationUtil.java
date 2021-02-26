package com.example.cardealer.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {

    <T> boolean isValid(T entity);

    // if the dto is not valid a set of the errors is made and we will filter
    // and take only the names of the errors, Ex if a title is not correct
    // we will print on the console Your title is not correct
    <T> Set<ConstraintViolation<T>> violations(T entity);
}
