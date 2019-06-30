package dao;

import domain.Professions;
import repository.TypeOfProfession;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Singleton
public class ProfessionsDAOBean implements ProfessionsDAO {

    @PersistenceContext(unitName = "fachmann")
    EntityManager em;

    @Override
    public Optional<Professions> getByProfession(TypeOfProfession profession) {
        Optional<Professions> result = em.createQuery("SELECT p FROM Professions p WHERE p.profession = :val",
                Professions.class)
                .setParameter("val", profession)
                .getResultStream()
                .findFirst();
        return result;
    }

    @Override
    public Optional<Long> getIdByProfession(TypeOfProfession profession) {
        Optional<Long> result = em.createQuery("SELECT p FROM Professions p WHERE p.profession = :val",
                Professions.class)
                .setParameter("val", profession)
                .getResultStream()
                .map(Professions::getId)
                .findFirst();
        return result;
    }

    @Override
    public List<Professions> getAll() {
        List<Professions> result = em.createQuery("SELECT p FROM Professions p", Professions.class)
                .getResultList();
        return result;
    }

    @Override
    public void save(Professions profession) {
        if (!getByProfession(profession.getProfession()).isPresent()) {
            em.persist(profession);
        } else {
            Optional<Long> maybeProf = getByProfession(profession.getProfession())
                    .map(Professions::getId);
            profession.setId(maybeProf.orElseThrow(NoSuchElementException::new));
            em.merge(profession);
        }
    }

    @Override
    public Optional<Professions> get(Long id) {
        Optional<Professions> result = Optional.of(em.find(Professions.class, id));
        return result;
    }

    @Override
    public void delete(Long id) {
        get(id).ifPresent((prof) -> em.remove(em.merge(prof)));
    }

    @Override
    public void deleteByProfession(TypeOfProfession profession) {
        Optional<Long> id = getIdByProfession(profession);
        if(id.isPresent()) {
            Professions prof = get(id.get()).get();
            em.remove(em.merge(prof));
        }
    }
}
