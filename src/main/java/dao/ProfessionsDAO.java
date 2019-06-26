package dao;

import domain.Professions;
import repository.TypeOfProfession;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface ProfessionsDAO {

    Optional<Professions> getByProfession(TypeOfProfession profession);

}
