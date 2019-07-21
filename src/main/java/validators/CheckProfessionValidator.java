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
import java.util.List;
import java.util.stream.Collectors;

public class CheckProfessionValidator implements ConstraintValidator<CheckProfession, Object> {


    @PersistenceContext(unitName = "fachmann")
    EntityManager em;

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

        List<String> profsInDatabase = em.createQuery("SELECT prof.profession FROM Professions prof", Professions.class)
                .getResultStream()
                .map(Professions::getProfession)
                .collect(Collectors.toList());


        if (object == null){
            return true;
        }

        return profsInDatabase.contains(object.toString());


    }
}
