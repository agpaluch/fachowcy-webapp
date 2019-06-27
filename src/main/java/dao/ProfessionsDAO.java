package dao;

import domain.Professions;
import repository.TypeOfProfession;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface ProfessionsDAO extends IRootDAO<Professions> {

    Optional<Professions> getByProfession(TypeOfProfession profession);

    Optional<Long> getIdByProfession(TypeOfProfession profession);

    void deleteByProfession(TypeOfProfession profession);

}
