package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;


@Singleton
public class UserLoginDAOBean implements UserLoginDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Optional<UserLogin> getByLogin(String email) {

        EntityManager em = entityManagerFactory.createEntityManager();

         return em.createQuery("SELECT ul FROM UserLogin ul WHERE ul.email = :val", UserLogin.class)
                .setParameter("val", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<Long> getIDbyLogin(String email) {
        Optional<UserLogin> userLogin = getByLogin(email);
        if(userLogin.isPresent()) {
            return Optional.of(userLogin.get().getId());
        }
        return Optional.empty();
    }

    @Override
    public UserDetails getDetailsByLogin(String email) {
        Optional<UserLogin> userLogin = getByLogin(email);
        if(userLogin.isPresent()) {
            return userLogin.get().getUserDetails();
        } else {
            logger.severe("NO USER FOUND");
            throw new NoSuchElementException("ddd");
        }
    }

    @Override
    public Role getRoleByLogin(String email) {
        return null;
    }

    @Override
    public List<UserLogin> getAll() {
        return null;
    }

    @Override
    public void save(UserLogin userLogin) {
        EntityManager entityManager = startTransaction();
        UserLogin ul = userLogin;
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

    @Override
    public Optional<UserLogin> get(Long id) {
        return null;
    }

    @Override
    public void update(UserLogin domain) {

    }

    @Override
    public void delete(UserLogin domain) {

    }

    private boolean doesAUserExist(String email) {
         return getByLogin(email).isPresent();
    }

}
