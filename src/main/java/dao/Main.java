package dao;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Main {


    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(50);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        for (ConstraintViolation<User> violation : violations) {
            System.out.println((violation.getMessage().isEmpty()));
            System.out.println(violation.getPropertyPath());
        }

    }

}
