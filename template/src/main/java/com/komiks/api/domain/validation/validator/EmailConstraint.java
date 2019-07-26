package com.khakiout.api.domain.validation.validator;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ReportAsSingleViolation
@NotNull
@Email(regexp = ".+@.+\\..+")
@NotEmpty
@Documented
@Target({FIELD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
/**
 * Validation constraint for name properties.
 */
public @interface EmailConstraint {

    /**
     * The error message.
     *
     * @return the error message, the default will be returned if not customized.
     */
    String message() default "Email must be valid.";

    /**
     * The groups where the constraint belongs.
     *
     * @return the constraint groups.
     */
    Class<?>[] groups() default {};

    /**
     * The payload to be validated.
     *
     * @return the payload.
     */
    Class<? extends Payload>[] payload() default {};

}
