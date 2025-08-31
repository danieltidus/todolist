package br.ufpb.dcx.dsc.todolist.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DCXEmailValidator.class)
@Documented
public @interface DCXEmail {
    String message() default "{DCXEmail.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
