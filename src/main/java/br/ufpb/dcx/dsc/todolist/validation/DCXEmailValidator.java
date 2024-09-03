package br.ufpb.dcx.dsc.todolist.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class DCXEmailValidator implements ConstraintValidator<DCXEmail, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<String> emailOptional = Optional.ofNullable(email);
        return emailOptional.map(e -> e.endsWith("@dcx.ufpb.br")).orElse(false);
    }
}
