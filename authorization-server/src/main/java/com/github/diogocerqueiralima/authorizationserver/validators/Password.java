package com.github.diogocerqueiralima.authorizationserver.validators;

import com.google.common.base.Joiner;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.passay.*;

import java.lang.annotation.*;
import java.util.List;

@Documented
@Constraint(validatedBy = Password.CustomPasswordValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "Invalid Password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class CustomPasswordValidator implements ConstraintValidator<Password, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {

            if (value == null || value.isBlank())
                return false;

            PasswordValidator validator = new PasswordValidator(
                    List.of(
                            new LengthRule(8, 30),
                            new CharacterRule(EnglishCharacterData.UpperCase, 1),
                            new CharacterRule(EnglishCharacterData.Digit, 1),
                            new CharacterRule(EnglishCharacterData.Special, 1),
                            new WhitespaceRule()
                    )
            );

            RuleResult result = validator.validate(new PasswordData(value));

            if (result.isValid())
                return true;

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(Joiner.on("\n").join(validator.getMessages(result)))
                    .addConstraintViolation();

            return false;
        }

    }

}
