package dao;

import domain.HibernateUtil;
import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;


@Stateless
public class UserLoginDAOBean implements UserLoginDAO {

    private final HibernateUtil hibernateUtil;
    Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    public UserLoginDAOBean(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    @Override
    public Optional<UserLogin> getByLogin(String email) {
        EntityManager em = hibernateUtil.getEntityManager();
         return em.createQuery("FROM UserLogin WHERE email = :val", UserLogin.class)
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
