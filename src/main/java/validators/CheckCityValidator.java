package validators;

import org.apache.commons.lang3.EnumUtils;
import repository.City;
import repository.TypeOfProfession;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCityValidator implements ConstraintValidator<CheckCity, City> {


    @Override
    public boolean isValid(City object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        return EnumUtils.isValidEnum(City.class, "CA");


/*        try{
            City city = City.valueOf(object);
            return true;
        } catch (Exception e){
            return false;
        }*/


    }
}
