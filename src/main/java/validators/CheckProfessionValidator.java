package validators;

import org.apache.commons.lang3.EnumUtils;
import repository.TypeOfProfession;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumSet;

public class CheckProfessionValidator implements ConstraintValidator<CheckProfession, TypeOfProfession> {



    @Override
    public boolean isValid(TypeOfProfession object, ConstraintValidatorContext constraintContext) {

        if (object == null){
            return true;
        }

        return EnumUtils.isValidEnum(TypeOfProfession.class, "CA");

        //return ((object != null) && EnumUtils.isValidEnum(TypeOfProfession.class, "CA"));

/*        try{
            TypeOfProfession profession = TypeOfProfession.valueOf(object);
            return true;
        } catch (Exception e){
            return false;
        }*/


    }
}
