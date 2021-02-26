package softuni.exam.util;


import javax.validation.Validator;

public class ValidatorUtilImpl implements ValidationUtil{

    private final Validator validator;


    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }

//    @Override
//    public <E> Set<ConstraintViolation<E>> violations(E entity) {
//        return validator.validate(entity);
//    }
}











