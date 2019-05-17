package validators;

import repository.TypeOfProfession;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckProfessionValidator implements ConstraintValidator<CheckProfession, String> {



    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }

        try{
            TypeOfProfession profession = TypeOfProfession.valueOf(object);
            return true;
        } catch (Exception e){
            return false;
        }


    }
}
