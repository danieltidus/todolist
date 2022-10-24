package br.ufpb.dcx.dsc.todolist.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DCXEmailValidator.class)
@Documented
public @interface DCXEmail {
    String message() default "Only emails ended with @dcx.ufpb.br are valid";

    Class<?>[] groups() default {};

    Class<? extends Payload []>[] payload() default { };
}
