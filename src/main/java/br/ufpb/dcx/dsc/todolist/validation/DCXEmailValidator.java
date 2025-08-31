package br.ufpb.dcx.dsc.todolist.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DCXEmailValidator implements ConstraintValidator<DCXEmail, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true; // Let @NotBlank handle null validation
        }
        return s.endsWith("@dcx.ufpb.br");
    }
}
