package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Singleton
public class UserLoginDAOBean implements UserLoginDAO {


    @PersistenceContext(unitName = "fachmann")
    EntityManager em;

    @Inject
    ProfessionsDAO professionsDAO;


    @Override
    public void delete(Long id) {
        get(id).ifPresent((ul) -> em.remove(em.merge(ul)));
    }

    @Override
    public void deleteByLogin(String email) {
       Optional<Long> id = getIDbyLogin(email);
       if(id.isPresent()) {
           UserLogin userLogin = get(id.get()).get();
           em.remove(em.merge(userLogin));
       }
    }

    @Override
    public Optional<UserLogin> get(Long id) {
        return Optional.of(em.find(UserLogin.class, id));
    }

    @Override
    public List<UserLogin> getAll() {
        return em.createQuery("SELECT ul FROM UserLogin ul", UserLogin.class)
                .getResultList();
    }

    @Override
    public Optional<UserLogin> getByLogin(String email) {
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

    private boolean isProfessional(UserLogin userLogin) {
        return userLogin.getRole().equals(Role.PROFESSIONAL);
    }


    public List<UserLogin> getProfByProfession(String profession) {
        return em.createQuery("SELECT ul FROM UserLogin ul JOIN ul.profession p WHERE p.profession=:val", UserLogin.class)
                .setParameter("val", profession)
                .getResultStream()
                .filter(this::isProfessional)
                .collect(Collectors.toList());
    }

    @Override
    public void save(UserLogin userLogin) {
        if (!doesAUserExist(userLogin.getEmail())) {
            em.persist(userLogin);
        } else {
            Optional<Long> userID = getIDbyLogin(userLogin.getEmail());
            userLogin.setId(userID.orElseThrow(() ->
                      new NoSuchElementException("ID not found")));
            em.merge(userLogin);
        }
    }


/*    public void updateById(UserLogin userLogin){
        EntityManager em = startTransaction();
        Optional<UserLogin> user = get(userLogin.getId());
        em.merge(userLogin);
        commit(em);
    }*/


    public boolean doesAUserExist(String email) {
         return getByLogin(email).isPresent();
    }

}
