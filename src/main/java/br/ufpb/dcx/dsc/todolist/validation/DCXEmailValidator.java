package br.ufpb.dcx.dsc.todolist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DCXEmailValidator implements ConstraintValidator<DCXEmail, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.endsWith("@dcx.ufpb.br");
    }
}
