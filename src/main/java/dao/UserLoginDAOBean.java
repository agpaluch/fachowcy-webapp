package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;
import repository.TypeOfProfession;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Singleton
public class UserLoginDAOBean implements UserLoginDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    @Override
    public void delete(Long id) {
        //EntityManager em = startTransaction();
        get(id).ifPresent((ul) -> {
            em.remove(em.merge(ul));
        });
        //commit(em);
    }

    @Override
    public void deleteByLogin(String email) {
       //EntityManager em = startTransaction();
       Optional<Long> id = getIDbyLogin(email);
       if(id.isPresent()) {
           UserLogin userLogin = get(id.get()).get();
           em.remove(em.merge(userLogin));
           //commit(em);
       }
    }

    @Override
    public Optional<UserLogin> get(Long id) {
        //EntityManager em = startTransaction();
        Optional<UserLogin> result = Optional.of(em.find(UserLogin.class, id));
        //commit(em);
        return result;
    }

    @Override
    public List<UserLogin> getAll() {
        //EntityManager em = startTransaction();
        List<UserLogin> result = em.createQuery("SELECT ul FROM UserLogin ul", UserLogin.class)
                .getResultList();
        //commit(em);
        return result;
    }

    @Override
    public Optional<UserLogin> getByLogin(String email) {
        //EntityManager em = startTransaction();
        Optional<UserLogin> result = em.createQuery("SELECT ul FROM UserLogin ul WHERE ul.email = :val", UserLogin.class)
                .setParameter("val", email)
                .getResultStream()
                .findFirst();
        //commit(em);
        return result;
    }

    @Override
    public Optional<UserDetails> getDetailsByLogin(String email) {
        Optional<UserLogin> userLogin = getByLogin(email);
        return userLogin.map(UserLogin::getUserDetails);
    }

    @Override
    public Optional<Long> getIDbyLogin(String email) {
        Optional<UserLogin> userLogin = getByLogin(email);
        return userLogin.map(UserLogin::getId);
    }

    @Override
    public Optional<Role> getRoleByLogin(String email) {
        Optional<UserLogin> userLogin = getByLogin(email);
        return userLogin.map(UserLogin::getRole);
    }

    private boolean isProfessional(UserLogin userLogin) {
        return userLogin.getRole().equals(Role.PROFESSIONAL);
    }

    @Override
    public List<UserLogin> getProfByProfession(TypeOfProfession profession) {
        //EntityManager em = startTransaction();
        List<UserLogin> result = em.createQuery("SELECT ul FROM UserLogin ul WHERE ul.profession = :val", UserLogin.class)
                .setParameter("val", profession)
                .getResultStream()
                .filter(this::isProfessional)
                .collect(Collectors.toList());
        //commit(em);
        return result;
    }

    @Override
    public void save(UserLogin userLogin) {
        //EntityManager em = startTransaction();
        if (!doesAUserExist(userLogin.getEmail())) {
            em.persist(userLogin);
        } else {
            Optional<Long> userID = getIDbyLogin(userLogin.getEmail());
            userLogin.setId(userID.orElseThrow(() ->
                      new NoSuchElementException("ID not found")));
            em.merge(userLogin);
        }
        //commit(em);
    }

    public boolean doesAUserExist(String email) {
         return getByLogin(email).isPresent();
    }

}
