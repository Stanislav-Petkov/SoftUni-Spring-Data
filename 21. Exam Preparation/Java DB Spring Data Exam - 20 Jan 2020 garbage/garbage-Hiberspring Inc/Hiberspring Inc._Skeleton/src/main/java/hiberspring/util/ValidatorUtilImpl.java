package hiberspring.util;


import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorUtilImpl implements ValidationUtil {

    private final Validator validator;

    public ValidatorUtilImpl() {
        this.validator = Validation
        .buildDefaultValidatorFactory()
        .getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
       return this.validator.validate(entity).isEmpty();
    }



}
