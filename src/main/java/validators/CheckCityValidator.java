package validators;

import repository.City;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCityValidator implements ConstraintValidator<CheckCity, String> {


    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        try{
            City city = City.valueOf(object);
            return true;
        } catch (Exception e){
            return false;
        }


    }
}
