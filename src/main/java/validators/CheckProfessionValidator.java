package validators;

import domain.Professions;
import domain.UserLogin;
import org.apache.commons.lang3.EnumUtils;
import repository.TypeOfProfession;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumSet;

public class CheckProfessionValidator implements ConstraintValidator<CheckProfession, Object> {


    @PersistenceContext(unitName = "fachmann")
    EntityManager em;

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

        em.createQuery("SELECT prof.profession FROM Professions prof", Professions.class)
                .getResultList();

        if (object == null){
            return true;
        }

        return EnumUtils.isValidEnum(TypeOfProfession.class, object.toString());


    }
}
