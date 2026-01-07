package org.snakeinc.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingPlayerValidator.class)
public @interface ExistingPlayer {
    String message() default "This player does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
