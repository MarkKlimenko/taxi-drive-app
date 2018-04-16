package systems.vostok.taxi.drive.app.util.validator

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import java.lang.annotation.*

@Documented
@Constraint(validatedBy = OneOfValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface OneOf {
    String message() default 'OneOf value validator failed'

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []

    String[] value()

    static class OneOfValidator implements ConstraintValidator<OneOf, String> {
        String[] targetValues

        @Override
        void initialize(OneOf oneOf) {
            targetValues = oneOf.value()
        }

        @Override
        boolean isValid(String value, ConstraintValidatorContext context) {
            value in targetValues
        }
    }
}