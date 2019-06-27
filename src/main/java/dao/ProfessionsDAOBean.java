package dao;

import domain.Professions;
import repository.TypeOfProfession;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Singleton
public class ProfessionsDAOBean extends TransactionsUtil implements ProfessionsDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

    @Override
    public Optional<Professions> getByProfession(TypeOfProfession profession) {

        EntityManager em = startTransaction();
        Optional<Professions> result = em.createQuery("SELECT p FROM Professions p WHERE p.profession = :val",
                Professions.class)
                .setParameter("val", profession)
                .getResultStream()
                .findFirst();
        commit(em);

        return result;
    }

    @Override
    public List<Professions> getAll() {
        return null;
    }

    @Override
    public void save(Professions profession) {
        EntityManager em = startTransaction();
        if (!getByProfession(profession.getProfession()).isPresent()) {
            em.persist(profession);
        } else {
            Optional<Long> maybeProf = getByProfession(profession.getProfession())
                    .map(Professions::getId);
            profession.setId(maybeProf.orElseThrow(NoSuchElementException::new));
            em.merge(profession);
        }
        commit(em);
    }

    @Override
    public Optional<Professions> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
