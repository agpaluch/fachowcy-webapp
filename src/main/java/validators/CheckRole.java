package validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckRoleValidator.class)
@Documented
@Repeatable(validators.CheckRole.List.class)
public @interface CheckRole {


        String message() default "Nie można przypisać użytkownikowi takiej roli.";

        Class<?>[] groups() default { };

        Class<? extends Payload>[] payload() default { };


        @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
        @Retention(RUNTIME)
        @Documented
        @interface List {
            validators.CheckRole[] value();
        }

}
