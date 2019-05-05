package validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCityValidator.class)
@Documented
@Repeatable(CheckCity.List.class)
public @interface CheckCity {

    String message() default "Takiego miasta nie ma w bazie obsługiwanych miast.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CheckCity[] value();
    }
}