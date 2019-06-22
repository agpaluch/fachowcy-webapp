package validators;

import domain.Role;
import org.apache.commons.lang3.EnumUtils;
import repository.City;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckRoleValidator implements ConstraintValidator<CheckRole, Object>{

        @Override
        public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
            if ( object == null ) {
                return true;
            }

            return EnumUtils.isValidEnum(Role.class, object.toString());


        }

}
