package ee.swan.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {EqualsPropertyValuesValidator.class})
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface EqualsPropertyValues {

    String message() default "{ee.swan.validation.EqualsPropertyValues.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String property();
    String comparingProperty();

    @Target({TYPE, ANNOTATION_TYPE,})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        EqualsPropertyValues[] value();
    }
}
