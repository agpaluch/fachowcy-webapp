package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;


@Singleton
public class UserLoginDAOBean implements UserLoginDAO {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    Logger logger = Logger.getLogger(getClass().getName());


    @Override
    public Optional<UserLogin> getByLogin(String email) {
         return entityManager.createQuery("FROM UserLogin WHERE email = :val", UserLogin.class)
                .setParameter("val", email)
                .getResultStream()
                .findFirst();
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
    public void save(UserLogin domain) {

    }

    @Override
    public UserLogin get(Long id) {
        return null;
    }

    @Override
    public void update(UserLogin domain) {

    }

    @Override
    public void delete(UserLogin domain) {

    }
}
