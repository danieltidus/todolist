package br.ufpb.dcx.dsc.todolist.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DCXEmailValidator implements ConstraintValidator<DCXEmail, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email != null)
            return email.endsWith("@dcx.ufpb.br");
        return true;
    }
}
