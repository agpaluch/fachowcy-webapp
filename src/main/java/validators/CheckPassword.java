package validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckPasswordValidator.class)
@Documented

public @interface CheckPassword {

    String message() default "Potwierdzenie hasła niepoprawne.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String first();
    String second();

    @Target({ANNOTATION_TYPE , TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CheckPassword[] value();
    }
}
