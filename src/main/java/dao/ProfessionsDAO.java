package dao;

import domain.Professions;
import repository.TypeOfProfession;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface ProfessionsDAO extends IRootDAO<Professions> {

    Optional<Professions> getByProfession(String profession);

    Optional<Long> getIdByProfession(String profession);

    void deleteByProfession(String profession);

}