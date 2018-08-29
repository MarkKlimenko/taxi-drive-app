package systems.vostok.taxi.drive.app.util.validator

import systems.vostok.taxi.drive.app.dao.domain.RideState

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import java.lang.annotation.*

@Documented
@Constraint(validatedBy = InRideStateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface InRideState {
    String message() default 'InRideStateValidator value validator failed'

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []

    static class InRideStateValidator implements ConstraintValidator<InRideState, String> {
        String[] targetValues

        @Override
        void initialize(InRideState oneOf) {
            targetValues = RideState.values().collect { it.state }
        }

        @Override
        boolean isValid(String value, ConstraintValidatorContext context) {
            value in targetValues
        }
    }
}