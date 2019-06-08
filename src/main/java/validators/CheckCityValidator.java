package validators;

import org.apache.commons.lang3.EnumUtils;
import repository.City;
import repository.TypeOfProfession;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCityValidator implements ConstraintValidator<CheckCity, Object> {


    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        return EnumUtils.isValidEnum(City.class, object.toString());


    }
}
