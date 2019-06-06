package validators;

import org.apache.commons.lang3.EnumUtils;
import repository.City;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckIfNotZeroValidator implements ConstraintValidator<CheckIfNotZero, Double> {


    @Override
    public boolean isValid(Double object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        return object!=0;


/*        try{
            City city = City.valueOf(object);
            return true;
        } catch (Exception e){
            return false;
        }*/


    }
}
