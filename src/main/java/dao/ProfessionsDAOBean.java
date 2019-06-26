package dao;

import domain.Professions;
import repository.TypeOfProfession;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import java.util.Optional;

@Singleton
public class ProfessionsDAOBean extends TransactionsUtil implements ProfessionsDAO {


    @Override
    public Optional<Professions> getByProfession(TypeOfProfession profession) {

        EntityManager em = startTransaction();
        Optional<Professions> result = em.createQuery("SELECT p FROM Professions p WHERE p.profession.profession = :val",
                Professions.class)
                .setParameter("val", profession)
                .getResultStream()
                .findFirst();
        commit(em);

        return result;
    }
}
