package validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, Object> {

    private String password;
    private String confirmPassword;


    @Override
    public void initialize(final CheckPassword constraintAnnotation) {
        password = constraintAnnotation.first();
        confirmPassword = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext constraintContext) {

        boolean isValid=false;
        try
        {
            final Object firstObj = BeanUtils.getProperty(object, password);
            final Object secondObj = BeanUtils.getProperty(object, confirmPassword);

            isValid =  (firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj));
        }
        catch (final Exception e)
        {
            isValid=false;
        }

/*        if ( !isValid ) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext
                    .buildConstraintViolationWithTemplate( "{my.custom.template}" )
                    .addPropertyNode( "password" ).addConstraintViolation();
        }*/


        return isValid;




    }
}
