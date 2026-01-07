package org.snakeinc.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.snakeinc.api.repository.PlayerRepo;

@RequiredArgsConstructor
public class ExistingPlayerValidator implements ConstraintValidator<ExistingPlayer, Integer> {

    private final PlayerRepo repository;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return repository.existsById(value);
    }
}
