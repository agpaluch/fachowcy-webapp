package dao;

import domain.Professions;
import repository.TypeOfProfession;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Singleton
public class ProfessionsDAOBean extends TransactionsUtil implements ProfessionsDAO {

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
    public Optional<Long> getIdByProfession(TypeOfProfession profession) {
        EntityManager em = startTransaction();
        Optional<Long> result = em.createQuery("SELECT p FROM Professions p WHERE p.profession = :val",
                Professions.class)
                .setParameter("val", profession)
                .getResultStream()
                .map(Professions::getId)
                .findFirst();
        commit(em);
        return result;
    }

    @Override
    public List<Professions> getAll() {
        EntityManager em = startTransaction();
        List<Professions> result = em.createQuery("SELECT p FROM Professions p", Professions.class)
                .getResultList();
        commit(em);
        return result;
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
        EntityManager em = startTransaction();
        Optional<Professions> result = Optional.of(em.find(Professions.class, id));
        commit(em);
        return result;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = startTransaction();
        get(id).ifPresent((prof) -> em.remove(em.merge(prof)));
        commit(em);
    }

    @Override
    public void deleteByProfession(TypeOfProfession profession) {
        EntityManager em = startTransaction();
        Optional<Long> id = getIdByProfession(profession);
        if(id.isPresent()) {
            Professions prof = get(id.get()).get();
            em.remove(em.merge(prof));
            commit(em);
        }
    }
}
