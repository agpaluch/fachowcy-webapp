package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Singleton
public class UserLoginDAOBean implements UserLoginDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

    @Override
    public void delete(Long id) {
        EntityManager em = startTransaction();
        get(id).ifPresent((ul) -> {
            em.remove(em.merge(ul));
        });
        commit(em);
    }


    @Override
    public void deleteByLogin(String email) {
       EntityManager em = startTransaction();
       Optional<Long> id = getIDbyLogin(email);
       if(id.isPresent()) {
           UserLogin userLogin = get(id.get()).get();
           em.remove(em.merge(userLogin));
           commit(em);
       }
    }



    @Override
    public Optional<UserLogin> get(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return Optional.of(em.find(UserLogin.class, id));
    }

    @Override
    public List<UserLogin> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT ul FROM UserLogin ul", UserLogin.class)
                .getResultList();
    }

    @Override
    public Optional<UserLogin> getByLogin(String email) {
        EntityManager em = entityManagerFactory.createEntityManager();
         return em.createQuery("SELECT ul FROM UserLogin ul WHERE ul.email = :val", UserLogin.class)
                .setParameter("val", email)
                .getResultStream()
                .findFirst();
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

    @Override
    public void save(UserLogin userLogin) {
        EntityManager entityManager = startTransaction();
        if (!doesAUserExist(userLogin.getEmail())) {
            entityManager.persist(userLogin);
        } else {
            Optional<Long> userID = getIDbyLogin(userLogin.getEmail());
            userLogin.setId(userID.orElseThrow(() ->
                      new NoSuchElementException("ID not found")));
            entityManager.merge(userLogin);
        }
        commit(entityManager);
    }

    private boolean doesAUserExist(String email) {
         return getByLogin(email).isPresent();
    }

}
